package com.foxboro.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.foxboro.entity.Materials;
import com.foxboro.entity.MaterialsCategory;
import com.foxboro.entity.MaterialsHistory;
import com.foxboro.entity.MaterialsOrder;
import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;
import com.foxboro.entity.Users;
import com.foxboro.service.MaterialsCategoryService;
import com.foxboro.service.MaterialsHistoryService;
import com.foxboro.service.MaterialsOrderService;
import com.foxboro.service.MaterialsService;
import com.foxboro.service.ProductService;
import com.foxboro.service.RoleService;
import com.foxboro.tools.Constants;
import com.foxboro.tools.ExcelToSql;
import com.foxboro.tools.JavaToExcel;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping(value="/mat")
public class MaterialsController {
	private Logger log=Logger.getLogger(MaterialsController.class);
	//存放packing的临时订购数据，由于每个user都有一个matList，故使用map存放，<username,ArrayList>
	//private ArrayList<MaterialsOrder> matList=new ArrayList<MaterialsOrder>();	//临时存放预定包材的集合
	private Map<String,ArrayList<MaterialsOrder>> matListMap=new HashMap<String,ArrayList<MaterialsOrder>>();	

	@Autowired
	private MaterialsService materialsService;
	@Autowired
	private MaterialsCategoryService materialsCategoryService;
	@Autowired
	private MaterialsHistoryService materialsHistoryService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialsOrderService materialsOrderService;

	//材料清单显示
	@RequestMapping("/materialsList")
	public String materialsList(HttpServletRequest request,Model model,Integer currentPage) throws UnsupportedEncodingException{
		String materialsName=request.getParameter("materialsName");
		//回显查询条件
		request.setAttribute("materialsName", materialsName);
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		int count=materialsService.countMaterials(materialsName);
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		List<Materials> mateList=materialsService.materialsList(materialsName, (currentPage-1)*maxSize, maxSize);
		model.addAttribute("mateList",mateList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("count", count);
		//调用按用户角色获取“包材”修改功能权限的方法
		Integer modifyStatus=getModifyStatus(request, "包材");
		model.addAttribute("modifyStatus",modifyStatus);
		return "packing/matList";
	}

	//按用户角色获取修改功能权限
	protected Integer getModifyStatus(HttpServletRequest request,String roleName){
		Integer result=0;
		Users currentUser=(Users) request.getSession().getAttribute("user");
		currentUser.setRoleName(roleName);
		if(roleService.getModifyStatueByUsername(currentUser)!=null){
			Integer modifyStatus=roleService.getModifyStatueByUsername(currentUser);
			if(modifyStatus==1){	//当角色为普通用户时才执行屏蔽增、删、改功能
				result= modifyStatus;
			}
		}
		return result;
	}

	//跳转至新增材料显示页面
	@RequestMapping("/addMaterialsCategoryWindow")
	public String  addMaterialsCategoryWindow(Model model){
		List<MaterialsCategory> MaterialsCategoryList=materialsCategoryService.getMaterialsCategoryList();
		model.addAttribute("MaterialsCategoryList",MaterialsCategoryList);
		return "packing/matadd";
	}

	//用ajax新增材料
	@RequestMapping(value="/addMaterialsMT.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addMaterialsMTajax(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam String materialsName,@RequestParam String materialsNum,
			@RequestParam String materialsCategoryNameId) throws IOException{
		Map<String,String> resultMap=new HashMap<String,String>();
		if(materialsService.getMaterialsByName(materialsName.toUpperCase())==null){//判断材料名称
			if(Integer.parseInt(materialsCategoryNameId)!=0){//判断材料分类
				Materials materials=new Materials();
				materials.setMaterialsName(materialsName.toUpperCase());
				materials.setMaterialsNum(Integer.parseInt(materialsNum));
				materials.setMaterialsCategoryNameId(Integer.parseInt(materialsCategoryNameId));	
				Users user=(Users) session.getAttribute("user");
				materials.setCreatedBy(user.getId());
				try {
					materialsService.addMaterialsMT(materials);//新增【材料信息】至【材料表】中
					materialsService.addHistory(materialsName,0, Integer.parseInt(materialsNum),1, user,0,"");//调用新增【材料信息】至【包材历史清单】中的方法  question
					resultMap.put("addResult", "success");//保存成功
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultMap.put("addResult", "false");//保存失败
				}
			}else{
				resultMap.put("addResult", "categoryfalse");//材料分类未选
			}
		}else{
			resultMap.put("addResult", "exsit");//材料名称已存在
		}
		return resultMap;
	}

	//用ajax提示新增材料的【材料名称】不可重复
	@RequestMapping("/addMaterialsNameCheck")
	@ResponseBody
	public String addMaterialsNameCheck(String materialsName){
		if(materialsService.getMaterialsByName(materialsName)==null){
			return "true";
		}else{
			return "false";
		}
	}

	//使用ajax删除材料信息
	@RequestMapping(value="/doDelMaterials.json",method=RequestMethod.GET)
	@ResponseBody
	public Object doDelMaterials(@RequestParam String id,@RequestParam String mName) throws IOException{
		HashMap<String,String> resultMap=new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(id)){
			resultMap.put("delresult","notexist");
		}else{
			try {
				boolean flag=true;
				ArrayList<Integer> materialsStatus=materialsHistoryService.getMaterialsHistoryStatus(mName);
				for (int i=0;i<materialsStatus.size();i++) {
					if(materialsStatus.get(i)!=1){//如果材料状态不为1，代表除了新建另外已有了其它操作，因此不予删除
						flag=false;
					}
				}
				if(flag==true){//可以删除
					materialsHistoryService.delMaterialsHistory(mName);
					materialsService.doDelMaterials(Integer.parseInt(id));
					resultMap.put("delresult","true");
				}else{//不予删除
					resultMap.put("delresult","exist");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultMap.put("delresult","false");
			}
		}
		return resultMap;
	}

	//跳转至更新材料页面
	@RequestMapping("/updateMaterialsWindow")
	public String updateMaterialsWindow(Model model,int id,HttpSession session){
		Materials materials=materialsService.getMaterialsById(id);
		model.addAttribute("materials",materials);
		session.setAttribute("oldMaterialsName", materials.getMaterialsName());
		List<MaterialsCategory> materialsCategory=materialsCategoryService.getMaterialsCategoryList();
		model.addAttribute("materialsCategory",materialsCategory);
		return "packing/matupdate";
	}

	//用ajax判断更新【材料名称】是否可用
	@RequestMapping("/updateMaterialsNameCheck")
	@ResponseBody
	public String updateMaterialsNameCheck(String materialsName,HttpSession session){
		String oldMaterialsName=(String) session.getAttribute("oldMaterialsName");
		if(oldMaterialsName.equals(materialsName.toUpperCase()) || materialsService.getMaterialsByName(materialsName)==null){
			return "true";
		}else{
			return "false";
		}
	}

	//修改材料信息
	@RequestMapping("/updateMaterials")
	public void updateMaterials(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();

		Materials materials=new Materials();
		String materialsName=request.getParameter("materialsName").toUpperCase();
		String oldMaterialsName=(String) session.getAttribute("oldMaterialsName");//提取材料名称和修改过的材料名称对比，相同也可以继续执行
		if(materialsService.getMaterialsByName(materialsName)==null || oldMaterialsName.equals(materialsName)){
			materials.setMaterialsName(materialsName);

			int materialsNum=Integer.parseInt(request.getParameter("materialsNum"));
			materials.setMaterialsNum(materialsNum);

			int materialsCategoryNameId=Integer.parseInt(request.getParameter("materialsCategoryNameId"));
			materials.setMaterialsCategoryNameId(materialsCategoryNameId);

			Users user=(Users) session.getAttribute("user");
			materials.setModifyBy(user.getId());

			int id=Integer.parseInt(request.getParameter("id"));
			materials.setId(id);

			try {
				materialsService.updateMaterials(materials);
				out.print("<script>alert('修改成功！');window.location.href='materialsList'</script>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<script>alert('修改失败！');window.location.href='materialsList'</script>");
			}
		}else{
			out.print("<script>alert('材料型号已存在！');window.location.href='updateMaterialsWindow'</script>");
		}
	}

	//跳转到材料入库页面
	@RequestMapping("/warehouseInWindow")
	public String warehouseInWindow(Model model,HttpServletRequest request){
		List<Materials> materials=materialsService.getAllMaterials();
		model.addAttribute("materials",materials);
		return "packing/warehouseIn";
	}

	//材料入库
	@RequestMapping(value="/warehouseIn.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object warehouseIn(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String id,@RequestParam String inNum) throws IOException{
		Map<String,String> resultMap=new HashMap<String,String>();
		if(!id.equals("0")){//判断是否已选择材料型号
			if(!inNum.equals("") && inNum!=null && !inNum.equals("请填写数量") && Integer.parseInt(inNum)!=0){//判断是否已填写数量
				try {
					Users user=(Users) request.getSession().getAttribute("user");
					int modifyBy=user.getId();
					materialsService.warehouseIn(Integer.parseInt(inNum), Integer.parseInt(id),modifyBy);//【材料入库】信息至【材料表】中
					materialsService.addHistory(null,Integer.parseInt(id), Integer.parseInt(inNum),2, user,0,"");//调用新增【材料信息】至【包材历史清单】中的方法
					resultMap.put("inResult", "success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					resultMap.put("inResult", "false");
				}
			}else{
				resultMap.put("inResult", "noexsit");
			}
		}else{
			resultMap.put("inResult", "noId");
		}
		return resultMap;
	}

	//材料入库页面根据【材料名称】用AJAX显示【库存数量】
	@RequestMapping("/warehouseInCheck.ajax")
	@ResponseBody
	public String warehouseInCheck(int id){
		int materialsNum=materialsService.getMaterialsById(id).getMaterialsNum();
		return String.valueOf(materialsNum);
	}

	//跳转到材料出库页面
	@RequestMapping("/warehouseOutWindow")
	public String warehouseOutWindow(Model model,HttpServletRequest request){
		List<Materials> materials=materialsService.getAllMaterials();
		model.addAttribute("materials",materials);
		return "packing/warehouseOut";
	}

	//材料出库
	@RequestMapping(value="/warehouseOut.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object warehouseOut(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String id,@RequestParam String outNum,
			@RequestParam String remark) throws IOException{
		Map<String,String> resultMap=new HashMap<String,String>();

		if(!id.equals("0")){//判断是否已选择材料型号
			if(!outNum.equals("") && outNum!=null && !outNum.equals("请填写数量") && Integer.parseInt(outNum)!=0){//判断是否已填写数量
				Users user=(Users) request.getSession().getAttribute("user");
				int modifyBy=user.getId();
				int materialsNum=materialsService.getMaterialsById(Integer.parseInt(id)).getMaterialsNum();//库存数量
				//出库数量与库存数量做对比
				if(Integer.parseInt(outNum)>materialsNum){
					resultMap.put("outResult", "notEnough");
					//out.print("<script>alert('库存不足，无法满足出库需求！');window.location.href='warehouseOutWindow';</script>");
				}else{
					try {
						materialsService.warehouseOut(Integer.parseInt(outNum), Integer.parseInt(id),modifyBy);//【材料出库】信息至【材料表】中
						materialsService.addHistory(null,Integer.parseInt(id), Integer.parseInt(outNum),3, user,0,remark);//调用新增【材料信息】至【包材历史清单】中的方法
						resultMap.put("outResult", "success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resultMap.put("outResult", "false");
					}
				}
			}else{
				resultMap.put("outResult", "noexsit");
			}
		}else{
			resultMap.put("outResult", "noId");
		}
		return resultMap;
	}

	//材料出库页面根据【材料名称】用AJAX显示【库存数量】
	@RequestMapping("/warehouseOutCheck.ajax")
	@ResponseBody
	public String warehouseOutCheck(int id){
		int materialsNum=materialsService.getMaterialsById(id).getMaterialsNum();
		return String.valueOf(materialsNum);
	}

	//产品使用包材
	@RequestMapping(value="/doUseMaterialsByPro.ajax",method=RequestMethod.POST)
	@ResponseBody
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Object useMaterialsByPro(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String box,@RequestParam String boxQuantity,
			@RequestParam String gasket,@RequestParam String gasketQuantity,
			@RequestParam String spongiaOne,@RequestParam String spongiaOneQuantity,
			@RequestParam String spongiaTwo,@RequestParam String spongiaTwoQuantity,
			@RequestParam String esdBag,@RequestParam String esdBagQuantity,
			@RequestParam String geDang,@RequestParam String geDangQuantity,
			@RequestParam String esdTable,@RequestParam String esdTableQuantity,
			@RequestParam String esdBubbleBag,@RequestParam String esdBubbleBagQuantity,
			@RequestParam String productCategoryId,@RequestParam String pwd,
			@RequestParam String pwdQuantity) throws IOException{
		Map<String,String> resultMap=new HashMap<String,String>();
		if(!productCategoryId.equals("0") && !pwd.equals("0")){
			if(pwdQuantity!=null && !pwdQuantity.equals("") && !pwdQuantity.equals("0")){
				//根据页面输入数据调用是否更新包材【库存信息】方法，结果放入List集合
				List<String> boxResult=materialsService.result(box,Integer.parseInt(boxQuantity),
						materialsService.getMaterialsNumByName(box));
				List<String> gasketResult=materialsService.result(gasket,Integer.parseInt(gasketQuantity), 
						materialsService.getMaterialsNumByName(gasket));
				List<String> spongiaOneResult=materialsService.result(spongiaOne,Integer.parseInt(spongiaOneQuantity), 
						materialsService.getMaterialsNumByName(spongiaOne));
				List<String> spongiaTwoResult=materialsService.result(spongiaTwo,Integer.parseInt(spongiaTwoQuantity), 
						materialsService.getMaterialsNumByName(spongiaTwo));
				List<String> esdBagResult=materialsService.result(esdBag,Integer.parseInt(esdBagQuantity), 
						materialsService.getMaterialsNumByName(esdBag));
				List<String> geDangResult=materialsService.result(geDang,Integer.parseInt(geDangQuantity), 
						materialsService.getMaterialsNumByName(geDang));
				List<String> esdTableResult=materialsService.result(esdTable,Integer.parseInt(esdTableQuantity), 
						materialsService.getMaterialsNumByName(esdTable));
				List<String> esdBubbleBagResult=materialsService.result(esdBubbleBag,Integer.parseInt(esdBubbleBagQuantity), 
						materialsService.getMaterialsNumByName(esdBubbleBag));

				//List结果集合放入Map集合
				Map<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
				hm.put("1", (ArrayList<String>) boxResult);
				hm.put("2", (ArrayList<String>) gasketResult);
				hm.put("3", (ArrayList<String>) spongiaOneResult);
				hm.put("4", (ArrayList<String>) spongiaTwoResult);
				hm.put("5", (ArrayList<String>) esdBagResult);
				hm.put("6", (ArrayList<String>) geDangResult);
				hm.put("7", (ArrayList<String>) esdTableResult);
				hm.put("8", (ArrayList<String>) esdBubbleBagResult);

				Integer productId=Integer.parseInt(pwd); //产品型号Id
				Users user=(Users) request.getSession().getAttribute("user");
				int modifyBy=user.getId();

				//事务回滚
				if(boxResult.get(3).equals("true") && gasketResult.get(3).equals("true") && 
						spongiaOneResult.get(3).equals("true") && spongiaTwoResult.get(3).equals("true") && 
						esdBagResult.get(3).equals("true") && geDangResult.get(3).equals("true") &&
						esdTableResult.get(3).equals("true") && esdBubbleBagResult.get(3).equals("true")){
					Iterator<Map.Entry<String, ArrayList<String>>> it=hm.entrySet().iterator();
					while(it.hasNext()){
						Entry<String, ArrayList<String>> arr=it.next();
						ArrayList<String> al=arr.getValue();
						if(al.get(0)!=null && !al.get(0).equals("")){
							//更新【包材使用】数据到【材料表】中
							materialsService.updataMaterialsNumByName(al.get(0), Integer.parseInt(al.get(1)),modifyBy);
							//调用新增【材料信息】至【包材历史清单】中的方法
							materialsService.addHistory(al.get(0),0,Integer.parseInt(al.get(2)),4, user,productId,"");
						}
					}
					resultMap.put("UseMaterialsByProResult", "success");
					//out.print("<script>alert('使用成功');window.location.href='/FoxboroPacking/pro/productUseMateWindow';</script>");
				}else{
					resultMap.put("UseMaterialsByProResult", "false");
					//out.print("<script>alert('使用失败！');window.location.href='/FoxboroPacking/pro/productUseMateWindow';</script>");
				}
			}else{
				resultMap.put("UseMaterialsByProResult", "noPwdQuantity");
			}
		}else{
			resultMap.put("UseMaterialsByProResult", "noProductCategoryId");
		}
		return resultMap;
	}

	//包材历史清单页面展示desc
	@RequestMapping(value="/materialHistoryWindows",method=RequestMethod.GET)
	public String materialHistoryWindows(Model model,Integer currentPage,HttpServletRequest request){
		//日期选择
		String rangeDate=null;
		String dateStart=null;//开始日期
		String dateEnd=null;//截止日期
		//选择日期为空则使用null,否则截取日期
		if(request.getParameter("range_date")!=null && !request.getParameter("range_date").equals("")){
			rangeDate=request.getParameter("range_date");
			String[] date=rangeDate.split("~");
			dateStart=date[0].trim();
			dateEnd=date[1].trim();
		}

		List<MaterialsHistory> historyList=new ArrayList<MaterialsHistory>();
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		MaterialsHistory materialsHistory=new MaterialsHistory();
		materialsHistory.setDateStart(dateStart);
		materialsHistory.setDateEnd(dateEnd);
		int count=materialsHistoryService.getMaterialsHistoryCount(materialsHistory);
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		historyList=materialsHistoryService.getAllMaterialsHistoryList((currentPage-1)*maxSize, maxSize,dateStart,dateEnd);
		//调用按用户角色获取“包材”修改功能权限的方法
		Integer modifyStatus=getModifyStatus(request, "包材");
		model.addAttribute("modifyStatus",modifyStatus);
		model.addAttribute("historyList",historyList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("count", count);
		return "packing/materialHistory";
	}

	//进行包材历史清单页面展示asc
	@RequestMapping(value="/dateAsc",method=RequestMethod.GET)
	public String dateDesc(Integer currentPage,Model model,HttpServletRequest request){
		List<MaterialsHistory> historyList=new ArrayList<MaterialsHistory>();
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		MaterialsHistory materialsHistory=new MaterialsHistory();
		int count=materialsHistoryService.getMaterialsHistoryCount(materialsHistory);
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		historyList=materialsHistoryService.getAllMaterialsHistoryListAsc((currentPage-1)*maxSize, maxSize);
		//调用按用户角色获取“包材”修改功能权限的方法
		Integer modifyStatus=getModifyStatus(request, "包材");
		model.addAttribute("modifyStatus",modifyStatus);
		model.addAttribute("historyList",historyList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("count", count);
		return "packing/materialHistory";
	}

	//将包材历史清单导入Excel中  #服务器端#
	/*@RequestMapping(value = "/export.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object export(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> resultMap=new HashMap<String,String>();
		try {
			//获取数据
			List<MaterialsHistory> historyList=new ArrayList<MaterialsHistory>();
			historyList=materialsHistoryService.getAllMaterialsHistoryList(0,0,null,null);
			//sheet名
			String sheetName = "包材历史清单表";
			//excel标题
			String[] title = {"编号","材料型号","材料分类","使用数量","产品型号","分类","使用者","操作时间","状态","备注"};
			//excel文件名
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long currentTime=System.currentTimeMillis();
			String removeStr=":";
			String time1=sdf1.format(currentTime).replace(removeStr, "");
			String fileName = sheetName+time1+".xls";

			//获取数据
			String[][] values=new String[historyList.size()][10];
			for(int i=0;i<historyList.size();i++){
				MaterialsHistory obj=historyList.get(i);
				values[i][0]=String.valueOf(obj.getId());
				values[i][1]=String.valueOf(obj.getMaterialsName());
				values[i][2]=String.valueOf(obj.getMaterialsCategoryName());
				values[i][3]=String.valueOf(obj.getMaterialsChangeNum());
				values[i][4]=obj.getPwd()==null?"":String.valueOf(obj.getPwd());
				values[i][5]=obj.getProductCategoryName()==null?"":String.valueOf(obj.getProductCategoryName());
				values[i][6]=String.valueOf(obj.getRealname());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=sdf.format(obj.getModifyDate());
				values[i][7]=String.valueOf(time);
				values[i][8]=String.valueOf(obj.getMaterialStatusName());
				values[i][9]=String.valueOf(obj.getRemark());
			}

			//创建保存文件夹
			String strPath="d:\\包材历史清单\\"+fileName;
			File file=new File(strPath);
			File fileParent=file.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}

			//输出Excel文件
			FileOutputStream output=new FileOutputStream("d:\\包材历史清单\\"+fileName);
			JavaToExcel.getHSSFWorkbookForMaterialsHistory(sheetName, title,values, null).write(output);
			output.flush();
			output.close();
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("result", "false");
		}
		return resultMap;
	}*/
	
	//将包材历史清单导入Excel中  #客户端#
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request,HttpServletResponse response){
		try {
			//获取数据
			List<MaterialsHistory> historyList=new ArrayList<MaterialsHistory>();
			historyList=materialsHistoryService.getAllMaterialsHistoryList(0,0,null,null);
			//sheet名
			String sheetName = "包材历史清单表";
			//excel标题
			String[] title = {"编号","材料型号","材料分类","使用数量","产品型号","分类","使用者","操作时间","状态","备注"};
			//excel文件名
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long currentTime=System.currentTimeMillis();
			String removeStr=":";
			String time1=sdf1.format(currentTime).replace(removeStr, "");
			String fileName = sheetName+time1+".xls";

			//获取数据
			String[][] values=new String[historyList.size()][10];
			for(int i=0;i<historyList.size();i++){
				MaterialsHistory obj=historyList.get(i);
				values[i][0]=String.valueOf(obj.getId());
				values[i][1]=String.valueOf(obj.getMaterialsName());
				values[i][2]=String.valueOf(obj.getMaterialsCategoryName());
				values[i][3]=String.valueOf(obj.getMaterialsChangeNum());
				/*log.info("obj.getPwd()==============="+obj.getPwd());
				if(obj.getPwd()!=null){
					values[i][4]=String.valueOf(obj.getPwd());
					log.info("有");
				}else{
					values[i][4]="";
					log.info("无");
				}*/
				values[i][4]=obj.getPwd()==null?"":String.valueOf(obj.getPwd());
				values[i][5]=obj.getProductCategoryName()==null?"":String.valueOf(obj.getProductCategoryName());
				values[i][6]=String.valueOf(obj.getRealname());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=sdf.format(obj.getModifyDate());
				values[i][7]=String.valueOf(time);
				values[i][8]=String.valueOf(obj.getMaterialStatusName());
				values[i][9]=String.valueOf(obj.getRemark());
			}

			//输出Excel文件
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
			OutputStream outputStream=response.getOutputStream();
			JavaToExcel.getHSSFWorkbookForMaterialsHistory(sheetName, title,values, null).write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	//撤销历史记录
	@RequestMapping(value="/cancelHistory.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object cancelHistory(String id){
		Map<String,String> result=new HashMap<String,String>();
		if(id !=null && !id.equals("")){
			try {
				materialsService.updateMaterialsNumById(Integer.parseInt(id));	//恢复材料数量
				materialsHistoryService.delMaterialsHistoryById(Integer.parseInt(id));	//删除历史记录
				result.put("result", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.put("result", "failed");
			}
		}else{
			result.put("result", "error");
		}
		return result;
	}

	//包材订购窗口
	@RequestMapping(value="/materialsOrderWindows",method=RequestMethod.GET)
	public String materialsOrderWindows(HttpSession session){
		Users currentUser=(Users) session.getAttribute("user");
		matListMap.remove(currentUser.getUsername());	//包材订购页面刷新时清除本用户下的订购内容

		return "packing/matOrder";
	}

	//包材订购（单笔添加）
	@RequestMapping(value="/addMaterialsOrder.ajax",method=RequestMethod.POST)
	@ResponseBody
	public synchronized Object saveMaterialsOrder(String pwd,String pwdQuantity,String margin,Model model,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		if(pwd!=null && !pwd.equals("") && pwdQuantity!=null && !pwdQuantity.equals("")){
			Users currentUser=(Users) session.getAttribute("user");
			Product pro=productService.getProListByPwd(pwd);
			if(pro!=null){
				if(margin==null || margin.equals("")){
					margin="0";
				}
				List<Integer> arrNum=calculateMaterials(pro,Integer.parseInt(pwdQuantity),Integer.parseInt(margin));	//调用计算产品所需包材数量的方法
				//遍历product类中的属性和值
				Class cls=pro.getClass();
				Field[] fields=cls.getDeclaredFields();
				MaterialsOrder mat=null;	//临时存放每个预定包材
				ArrayList<MaterialsOrder> matList=new ArrayList<MaterialsOrder>();	//临时存放预定包材的集合
				int j=0;	//遍历arrNum的起始下标
				for(int i=3;i<13;i++){	//只截取product类中的3~13属性和其值	#屏蔽静电袋功能#
					Field f=fields[i];
					f.setAccessible(true);
					try {
						if(i%2!=0){		//奇数为name
							mat=new MaterialsOrder();
							if(f.get(pro)!=null && !f.get(pro).equals("")){	//此产品有使用此包材则set	  注：不为null且不为空
								String materialsName=(String)f.get(pro);	//材料名称
								//log.debug(""+matListMap.get(currentUser.getRealname()));
								if( matListMap.get(currentUser.getUsername())!=null && matListMap.get(currentUser.getUsername()).size()>0){	//临时材料集合中有数据
									log.debug("临时材料集合中有数据");
									matList=matListMap.get(currentUser.getUsername());
									for (int k=0;k<matListMap.get(currentUser.getUsername()).size();k++) {
										if(!matListMap.get(currentUser.getUsername()).get(k).getMaterialsName().equals(materialsName)){ //表里无此材料则添加信息
											//log.debug("表里无此材料则添加信息");
											mat.setMaterialsName(materialsName);
											//根据材料名称查类别名称
											String materialsCategoryName=materialsCategoryService.getMaterialsCategoryByMaterialsName(materialsName);
											mat.setMaterialsCategoryName(materialsCategoryName);
										}else{	//有此材料进行修改操作，并i++跳过数量set，且不执行add方法，并j++指向下一个包材的数量
											//log.debug("表里有此材料则添加信息");
											mat.setMaterialsName(materialsName);
											//根据材料名称查类别名称
											String materialsCategoryName=materialsCategoryService.getMaterialsCategoryByMaterialsName(materialsName);
											mat.setMaterialsCategoryName(materialsCategoryName);
											mat.setMaterialsNum(matListMap.get(currentUser.getUsername()).get(k).getMaterialsNum()+arrNum.get(j));

											matList.set(k,mat);	//把修改后的mat放入Map中
											i++;j++;
										}
									}
								}else{	//临时材料集合中无数据
									//log.debug("临时材料集合中无数据");
									mat.setMaterialsName(materialsName);
									//根据材料名称查类别名称
									String materialsCategoryName=materialsCategoryService.getMaterialsCategoryByMaterialsName(materialsName);
									mat.setMaterialsCategoryName(materialsCategoryName);
								}
							}else{	//此产品未使用此包材则不set，并i++跳过数量set，且不执行add方法
								i++;
							}	
						}else{		//偶数为num
							mat.setMaterialsNum(arrNum.get(j));
							mat.setPwd(pwd);
							mat.setPwdQuantity(Integer.parseInt(pwdQuantity));
							j++;	//arrNum下标+1，指向下一个包材的数量
							matList.add(mat);	//数量遍历后再存入list中	
							//materialsOrderService.addMaterialsOrder(mat);	//将数据存入SQL中
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				matListMap.put(currentUser.getUsername(), matList);	//数量遍历后再存入map中	
				String fastjson=JSON.toJSONString(matListMap.get(currentUser.getUsername()));
				model.addAttribute("pwd", pwd);
				model.addAttribute("pwdQuantity",pwdQuantity);
				result.put("result",fastjson);
			}else{	//无此产品包材工艺
				result.put("result", "noexsit");
			}
		}else{	//输入值为空
			result.put("result", "empty");
		}
		return result;
	}

	//包材订购（从excel表中批量添加）
	//ajax提交
	@RequestMapping(value="/addMaterialsOrderFromFile.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addMaterialsOrderFromFile(@RequestParam("file") MultipartFile excel,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		try {
			List<MaterialsOrder> materialsOrder=new ArrayList<MaterialsOrder>();
			if(!excel.isEmpty()){//判断文件是否有内容
				materialsOrder=ExcelToSql.getMaterialsOrder(excel);
				String fastjson=JSON.toJSONString(materialsOrder);
				result.put("result", fastjson);
			}else{
				result.put("result", "empty");
			}
		} catch(Exception e){
			result.put("result", "failed");
		}
		return result;
	}

	/**
	 * 计算产品所需包材数量
	 * @param pro	产品基础数据
	 * @param pwdQuantity	产品数量
	 * @param margin	余量
	 * @return	List<Integer>计算后的包材数量
	 */
	protected List<Integer> calculateMaterials(Product pro,int pwdTemp,int margin){
		List<Integer> arrNum=new ArrayList<Integer>();	//单个产品所使用各型号包材数量的集合
		double marginTemp=Double.valueOf(margin)/100;
		//包装盒
		if(pro.getBox()!=null && !pro.getBox().equals("")){
			//注意：计算时需要转为double，结果再转为int
			//总数乘以余量(1+marginTemp)，静电袋不参与余量计算
			//预计产品数%工艺中每盒产品数==0？向上取整（工艺中每盒的包装盒数/工艺中每盒产品数*预计生产数）：向上取整（工艺中每盒的包装盒数/工艺中每盒产品数*预计生产数+1）
			double tempNum=pwdTemp%pro.getPwdQuantity()==0?Math.floor((double)pro.getBoxQuantity()/pro.getPwdQuantity()*pwdTemp):
				Math.floor((double)pro.getBoxQuantity()/pro.getPwdQuantity()*pwdTemp+1);
			arrNum.add((int)Math.ceil((tempNum*(1+marginTemp))));
		}
		//衬垫
		if(pro.getGasket()!=null && !pro.getGasket().equals("")){
			//预计产品数%工艺中每盒产品数==0？向上取整（工艺中每盒的衬垫数/工艺中每盒产品数*预计生产数）：向上取整（工艺中每盒的衬垫数/工艺中每盒产品数*预计生产数+1）
			double tempNum=pwdTemp%pro.getPwdQuantity()==0?Math.floor((double)pro.getGasketQuantity()/pro.getPwdQuantity()*pwdTemp):
				Math.floor(((double)pro.getGasketQuantity()/pro.getPwdQuantity()*pwdTemp)+1);
			arrNum.add((int)Math.ceil((tempNum*(1+marginTemp))));
		}
		//变化海绵
		if(pro.getSpongiaOne()!=null && !pro.getSpongiaOne().equals("")){
			//预计产品数%工艺中每盒产品数==0？向上取整（工艺中每盒的变化海绵数/工艺中每盒产品数*预计生产数）：向上取整（工艺中每盒的变化海绵数/工艺中每盒产品数*预计生产数+1）
			double tempNum=pwdTemp%pro.getPwdQuantity()==0?Math.floor((double)pro.getSpongiaOneQuantity()/pro.getPwdQuantity()*pwdTemp):
				Math.floor(((double)pro.getSpongiaOneQuantity()/pro.getPwdQuantity()*pwdTemp)+1);
			arrNum.add((int)Math.ceil((tempNum*(1+marginTemp))));
		}
		//固定海绵
		if(pro.getSpongiaTwo()!=null && !pro.getSpongiaTwo().equals("")){
			//需要包装盒的数*固定海绵数
			//预计产品数%工艺中每盒产品数==0？向上取整（工艺中每盒的包装盒数/工艺中每盒产品数*预计生产数*固定海绵数）：向上取整（（工艺中每盒的包装盒数/工艺中每盒产品数*预计生产数+1）*固定海绵数）
			double tempNum=pwdTemp%pro.getPwdQuantity()==0?(Math.floor((double)pro.getBoxQuantity()/pro.getPwdQuantity()*pwdTemp)*pro.getSpongiaTwoQuantity()):
				(Math.floor(((double)pro.getBoxQuantity()/pro.getPwdQuantity()*pwdTemp)+1)*pro.getSpongiaTwoQuantity());
			arrNum.add((int)Math.ceil((tempNum*(1+marginTemp))));
		}
		//静电袋 #屏蔽此功能#
		/*if(pro.getEsdBag()!=null && !pro.getEsdBag().equals("")){
			//和预计产品数相同
			arrNum.add(pwdTemp);
		}*/
		//格挡
		if(pro.getGeDang()!=null && !pro.getGeDang().equals("")){
			//预计产品数%工艺中每盒产品数==0？向上取整（工艺中每盒的格挡盒数/工艺中每盒产品数*预计生产数）：向上取整（工艺中每盒的格挡数/工艺中每盒产品数*预计生产数+1）
			double tempNum=pwdTemp%pro.getPwdQuantity()==0?Math.floor((double)pro.getGeDangQuantity()/pro.getPwdQuantity()*pwdTemp):
				Math.floor(((double)pro.getGeDangQuantity()/pro.getPwdQuantity()*pwdTemp)+1);
			arrNum.add((int)Math.ceil((tempNum*(1+marginTemp))));
		}
		return arrNum;
	}

	//订购包材表格保存到SQL并导出到  #服务器#
	/*@RequestMapping(value="/saveAndExportToExcel.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object saveAndExportToExcel(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Map<String,String> result=new HashMap<String,String>();
		Users currentUser=(Users) session.getAttribute("user");
		ArrayList<MaterialsOrder> materialsOrderList=matListMap.get(currentUser.getUsername());
		MaterialsOrder materialsOrder=new MaterialsOrder();
		long code=System.currentTimeMillis();	//时间戳
		try {
			//sheet名
			String sheetName = "包材订购";
			//excel标题
			String[] title = {"成本中心","车间申请人","包材型号","数量","备注"};
			//excel文件名
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long currentTime=System.currentTimeMillis();
			String removeStr=":";
			String time1=sdf1.format(currentTime).replace(removeStr, "");
			String fileName = sheetName+time1+".xls";
			//获取数据
			String[][] values=new String[materialsOrderList.size()][5];

			for (int i=0;i<materialsOrderList.size();i++) {
				materialsOrder.setPwd(materialsOrderList.get(i).getPwd());
				materialsOrder.setPwdQuantity(materialsOrderList.get(i).getPwdQuantity());
				materialsOrder.setMaterialsName(materialsOrderList.get(i).getMaterialsName());
				materialsOrder.setMaterialsNum(materialsOrderList.get(i).getMaterialsNum());
				materialsOrder.setMaterialsCategoryName(materialsOrderList.get(i).getMaterialsCategoryName());
				materialsOrder.setOrderBy(currentUser.getId());
				materialsOrder.setCode(code);
				materialsOrder.setMargin(materialsOrderList.get(i).getMargin());
				materialsOrderService.addMaterialsOrder(materialsOrder);	//数据存入SQL

				//获取数据
				values[i][0]=String.valueOf("10441303");
				values[i][1]=String.valueOf(currentUser.getRealname());
				values[i][2]=String.valueOf(materialsOrder.getMaterialsName());
				values[i][3]=String.valueOf(materialsOrder.getMaterialsNum());
			}

			//创建保存文件夹
			String strPath="d:\\包材订购\\"+fileName;
			File file=new File(strPath);
			File fileParent=file.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}

			//输出Excel文件
			FileOutputStream output=new FileOutputStream("d:\\包材订购\\"+fileName);
			JavaToExcel.getHSSFWorkbookForMaterialsOrder(sheetName, title,values, null).write(output);
			output.flush();
			output.close();
			result.put("result", "success");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}*/

	//订购包材表格保存到SQL并导出到  #客户端#
	@RequestMapping(value="/saveAndExportToExcelClient")
	public void saveAndExportToExcelClient(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Users currentUser=(Users) session.getAttribute("user");
		ArrayList<MaterialsOrder> materialsOrderList=matListMap.get(currentUser.getUsername());
		MaterialsOrder materialsOrder=new MaterialsOrder();
		long code=System.currentTimeMillis();	//时间戳
		//sheet名
		String sheetName = "包材订购";
		//excel标题
		String[] title = {"成本中心","车间申请人","包材型号","数量","备注"};
		//excel文件名
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long currentTime=System.currentTimeMillis();
		String removeStr=":";
		String time1=sdf1.format(currentTime).replace(removeStr, "");
		String fileName = sheetName+time1+".xls";
		//获取数据
		if(materialsOrderList!=null && materialsOrderList.size()!=0){
			String[][] values=new String[materialsOrderList.size()][5];
				try {
					for (int i=0;i<materialsOrderList.size();i++) {
						materialsOrder.setPwd(materialsOrderList.get(i).getPwd());
						materialsOrder.setPwdQuantity(materialsOrderList.get(i).getPwdQuantity());
						materialsOrder.setMaterialsName(materialsOrderList.get(i).getMaterialsName());
						materialsOrder.setMaterialsNum(materialsOrderList.get(i).getMaterialsNum());
						materialsOrder.setMaterialsCategoryName(materialsOrderList.get(i).getMaterialsCategoryName());
						materialsOrder.setOrderBy(currentUser.getId());
						materialsOrder.setCode(code);
						materialsOrder.setMargin(materialsOrderList.get(i).getMargin());
						materialsOrderService.addMaterialsOrder(materialsOrder);	//数据存入SQL

						//获取数据
						values[i][0]=String.valueOf("10441303");
						values[i][1]=String.valueOf(currentUser.getRealname());
						values[i][2]=String.valueOf(materialsOrder.getMaterialsName());
						values[i][3]=String.valueOf(materialsOrder.getMaterialsNum());
					}

					//输出Excel文件
					response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
					OutputStream outputStream=response.getOutputStream();
					JavaToExcel.getHSSFWorkbookForMaterialsOrder(sheetName, title,values, null).write(outputStream);
					outputStream.flush();
					outputStream.close();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}

package com.foxboro.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;
import com.foxboro.entity.Users;
import com.foxboro.service.MaterialsHistoryService;
import com.foxboro.service.MaterialsService;
import com.foxboro.service.ProductCategoryService;
import com.foxboro.service.ProductService;
import com.foxboro.service.RoleService;
import com.foxboro.tools.Constants;
import com.foxboro.tools.ExcelToSql;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping(value="/pro")
public class ProductController {
	private Logger log=Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private MaterialsService materialsService;
	@Autowired
	private MaterialsHistoryService materialsHistoryService;
	@Autowired
	private RoleService roleService;

	//按条件查询包材信息
	@RequestMapping("/searchProList")
	public String getProListByCondition(HttpServletRequest request,Model model,Integer currentPage) {
		//取前台传入值
		String pwd=request.getParameter("pwd");
		String box=request.getParameter("box");
		String gasket=request.getParameter("gasket");
		String spongiaOne=request.getParameter("spongiaOne");
		String spongiaTwo=request.getParameter("spongiaTwo");
		int productCategoryId=0;
		if(request.getParameter("productCategoryId")!=null){
			productCategoryId=Integer.parseInt(request.getParameter("productCategoryId"));
		}
		//回显查询条件
		request.setAttribute("pwd", pwd);
		request.setAttribute("box", box);
		request.setAttribute("gasket", gasket);
		request.setAttribute("spongiaOne", spongiaOne);
		request.setAttribute("spongiaTwo", spongiaTwo);
		request.setAttribute("productCategoryId", productCategoryId);
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		int count=productService.countByCondition(pwd, box, gasket, spongiaOne, spongiaTwo, productCategoryId);
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		List<Product> ProList=productService.getProListByCondition(pwd, box, gasket, spongiaOne, spongiaTwo, productCategoryId, (currentPage-1)*maxSize, maxSize);
		model.addAttribute("ProList",ProList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("count",count);
		List<ProductCategory> cateList=productCategoryService.getCateList();
		model.addAttribute("cateList",cateList);
		//调用按用户角色获取“包材”修改功能权限的方法
		Integer modifyStatus=getModifyStatus(request, "包材");
		model.addAttribute("modifyStatus",modifyStatus);
		return "packing/proList";
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

	//跳转到产品包装工艺新增页面
	@RequestMapping("/addProWindow")
	public String addProWindow(Model model){
		List<ProductCategory> cateList=productCategoryService.getCateList();
		model.addAttribute("cateList",cateList);
		return "packing/proadd";
	}

	//新增产品包材工艺
	@RequestMapping("/addPro")
	public void addPro(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		PrintWriter out=response.getWriter();

		Product product=new Product();
		String pwd=request.getParameter("pwd").toUpperCase();
		if(productService.getPro(pwd)==1){
			out.print("<script>alert('产品已存在，请重新输入');window.location.href='addProWindow'</script>");
		}else{
			product.setPwd(pwd);
			Integer pwdQuantity=Integer.parseInt(request.getParameter("pwdQuantity"));
			product.setPwdQuantity(pwdQuantity);

			String box=request.getParameter("box").toUpperCase();
			product.setBox(box);
			Integer boxQuantity=Integer.parseInt(request.getParameter("boxQuantity"));
			product.setBoxQuantity(boxQuantity);

			String gasket=request.getParameter("gasket").toUpperCase();
			product.setGasket(gasket);
			Integer gasketQuantity=null;
			if(!request.getParameter("gasketQuantity").equals("")){
				gasketQuantity = Integer.parseInt(request.getParameter("gasketQuantity"));
				product.setGasketQuantity(gasketQuantity);
			}

			String spongiaOne=request.getParameter("spongiaOne").toUpperCase();
			product.setSpongiaOne(spongiaOne);
			Integer spongiaOneQuantity=null;
			if(!request.getParameter("spongiaOneQuantity").equals("")){
				spongiaOneQuantity=Integer.parseInt(request.getParameter("spongiaOneQuantity"));
				product.setSpongiaOneQuantity(spongiaOneQuantity);
			}

			String spongiaTwo=request.getParameter("spongiaTwo").toUpperCase();
			product.setSpongiaTwo(spongiaTwo);
			Integer spongiaTwoQuantity=null;
			if(!request.getParameter("spongiaTwoQuantity").equals("")){
				spongiaTwoQuantity=Integer.parseInt(request.getParameter("spongiaTwoQuantity"));
				product.setSpongiaTwoQuantity(spongiaTwoQuantity);
			}

			String esdBag=request.getParameter("esdBag").toUpperCase();
			product.setEsdBag(esdBag);
			Integer esdBagQuantity=null;
			if(!request.getParameter("esdBagQuantity").equals("")){
				esdBagQuantity=Integer.parseInt(request.getParameter("esdBagQuantity"));
				product.setEsdBagQuantity(esdBagQuantity);
			}

			String geDang=request.getParameter("geDang").toUpperCase();
			product.setGeDang(geDang);
			Integer geDangQuantity=null;
			if(!request.getParameter("geDangQuantity").equals("")){
				geDangQuantity=Integer.parseInt(request.getParameter("geDangQuantity"));
				product.setGeDangQuantity(geDangQuantity);
			}
			
			String esdTable=request.getParameter("esdTable").toUpperCase();
			product.setEsdTable(esdTable);
			Integer esdTableQuantity=null;
			if(!request.getParameter("esdTableQuantity").equals("")){
				esdTableQuantity=Integer.parseInt(request.getParameter("esdTableQuantity"));
				product.setEsdTableQuantity(esdTableQuantity);
			}

			String esdBubbleBag=request.getParameter("esdBubbleBag").toUpperCase();
			product.setEsdBubbleBag(esdBubbleBag);
			Integer esdBubbleBagQuantity=null;
			if(!request.getParameter("esdBubbleBagQuantity").equals("")){
				esdBubbleBagQuantity=Integer.parseInt(request.getParameter("esdBubbleBagQuantity"));
				product.setEsdBubbleBagQuantity(esdBubbleBagQuantity);
			}

			String remark=request.getParameter("remark");
			product.setRemark(remark);
			Integer productCategoryId=Integer.parseInt(request.getParameter("productCategoryId"));
			product.setProductCategoryId(productCategoryId);	

			try {
				productService.addProduct(product);
				out.print("<script>alert('保存成功！');window.location.href='searchProList'</script>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<script>alert('保存失败！');window.location.href='searchProList'</script>");
			}
		}
	}

	//使用ajax删除产品包装工艺
	@RequestMapping(value="/doDelPro.json",method=RequestMethod.GET)
	@ResponseBody
	public Object delPro(@RequestParam String id,@RequestParam String pwd){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(id)){
			resultMap.put("delresult", "notexist");
		}else{
			try {
				//判断“包材历史清单”中是否有要删除的产品信息
				int count=materialsHistoryService.getCountByPwd(pwd);
				if(count==0){
					productService.delPro(Integer.parseInt(id));
					resultMap.put("delresult", "true");
				}else{
					resultMap.put("delresult", "exist");
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultMap.put("delresult", "false");
			}
		}
		return resultMap;
	}

	//跳转到产品包装工艺更新页面
	@RequestMapping("/updatePro")
	public String updatePro(int id,Model model,HttpSession session){
		Product product=productService.getProById(id);
		List<ProductCategory> cateList=productCategoryService.getCateList();
		model.addAttribute("product",product);
		model.addAttribute("cateList",cateList);
		session.setAttribute("oldPwd", product.getPwd());
		return "packing/proupdate";
	}

	//根据id修改产品包材工艺
	@RequestMapping("/doUpdatePro")
	public void doUpdatePro(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session) throws IOException{
		PrintWriter out=response.getWriter();

		Product product=new Product();
		String pwd=request.getParameter("pwd").toUpperCase();
		String oldPwd=(String) session.getAttribute("oldPwd");	//跳转到更新页面时的pwd,如果相同则也可以执行更新操作
		if(productService.getPro(pwd)==1 && !pwd.equals(oldPwd)){
			out.print("<script>alert('产品已存在，请重新输入');window.location.href='addProWindow'</script>");
		}else{
			product.setPwd(pwd);
			Integer pwdQuantity=Integer.parseInt(request.getParameter("pwdQuantity"));
			product.setPwdQuantity(pwdQuantity);

			String box=request.getParameter("box").toUpperCase();
			product.setBox(box);
			Integer boxQuantity=Integer.parseInt(request.getParameter("boxQuantity"));
			product.setBoxQuantity(boxQuantity);

			String gasket=request.getParameter("gasket").toUpperCase();
			product.setGasket(gasket);
			Integer gasketQuantity=null;
			if(!request.getParameter("gasketQuantity").equals("")){
				gasketQuantity = Integer.parseInt(request.getParameter("gasketQuantity"));
				product.setGasketQuantity(gasketQuantity);
			}

			String spongiaOne=request.getParameter("spongiaOne").toUpperCase();
			product.setSpongiaOne(spongiaOne);
			Integer spongiaOneQuantity=null;
			if(request.getParameter("spongiaOneQuantity").equals("")){
				product.setSpongiaOneQuantity(0);
			}else{
				spongiaOneQuantity=Integer.parseInt(request.getParameter("spongiaOneQuantity"));
				product.setSpongiaOneQuantity(spongiaOneQuantity);
			}

			String spongiaTwo=request.getParameter("spongiaTwo").toUpperCase();
			product.setSpongiaTwo(spongiaTwo);
			Integer spongiaTwoQuantity=null;
			if(request.getParameter("spongiaTwoQuantity").equals("")){
				product.setSpongiaTwoQuantity(0);
			}else{
				spongiaTwoQuantity=Integer.parseInt(request.getParameter("spongiaTwoQuantity"));
				product.setSpongiaTwoQuantity(spongiaTwoQuantity);
			}

			String esdBag=request.getParameter("esdBag").toUpperCase();
			product.setEsdBag(esdBag);
			Integer esdBagQuantity=null;
			if(request.getParameter("esdBagQuantity").equals("")){
				product.setEsdBagQuantity(0);
			}else{
				esdBagQuantity=Integer.parseInt(request.getParameter("esdBagQuantity"));
				product.setEsdBagQuantity(esdBagQuantity);
			}

			String geDang=request.getParameter("geDang").toUpperCase();
			product.setGeDang(geDang);
			Integer geDangQuantity=null;
			if(request.getParameter("geDangQuantity").equals("")){
				product.setGeDangQuantity(0);
			}else{
				geDangQuantity=Integer.parseInt(request.getParameter("geDangQuantity"));
				product.setGeDangQuantity(geDangQuantity);
			}

			String esdTable=request.getParameter("esdTable").toUpperCase();
			product.setEsdTable(esdTable);
			Integer esdTableQuantity=null;
			if(request.getParameter("esdTableQuantity").equals("")){
				product.setEsdTableQuantity(0);
			}else{
				esdTableQuantity=Integer.parseInt(request.getParameter("esdTableQuantity"));
				product.setEsdTableQuantity(esdTableQuantity);
			}

			String esdBubbleBag=request.getParameter("esdBubbleBag").toUpperCase();
			product.setEsdBubbleBag(esdBubbleBag);
			Integer esdBubbleBagQuantity=null;
			if(request.getParameter("esdBubbleBagQuantity").equals("")){
				product.setEsdBubbleBagQuantity(0);
			}else{
				esdBubbleBagQuantity=Integer.parseInt(request.getParameter("esdBubbleBagQuantity"));
				product.setEsdBubbleBagQuantity(esdBubbleBagQuantity);
			}

			String remark=request.getParameter("remark");
			product.setRemark(remark);

			Integer productCategoryId=Integer.parseInt(request.getParameter("productCategoryId"));
			product.setProductCategoryId(productCategoryId);	

			int id=Integer.parseInt(request.getParameter("id"));
			product.setId(id);

			try {
				productService.updatePro(product);
				out.print("<script>alert('修改成功！');window.location.href='searchProList'</script>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<script>alert('修改失败！');window.location.href='searchProList'</script>");
			}
		}
	}

	//添加产品工艺时的pwd名称验证是否存在(ajax)
	@RequestMapping("/addPwdCheck")
	@ResponseBody
	public String addPwdCheck(String pwd){
		Product product=productService.getProListByPwd(pwd);
		if(product==null){
			return "true";//可以使用
		}else{
			return "false";//产品型号重复
		}
	}

	//更新产品工艺时的pwd名称验证是否存在(ajax)
	@RequestMapping("/updataPwdCheck")
	@ResponseBody
	public String updataPwdCheck(String pwd,HttpSession session){
		Product product=productService.getProListByPwd(pwd);
		String oldPwd=(String) session.getAttribute("oldPwd");//跳转到更新页面时的pwd,如果相同则也可以执行更新操作
		if(product==null || product.getPwd().equals(oldPwd)){
			return "true";//可以使用
		}else{
			return "false";//产品型号重复
		}
	}

	//跳转到产品使用包材页面
	@RequestMapping("/productUseMateWindow")
	public String productUseMateWindow(Model model,HttpSession session){
		List<ProductCategory> cateList=productCategoryService.getCateList();
		model.addAttribute("cateList",cateList);
		//判断管理员或普通用户
		/*Users user=(Users) session.getAttribute("user");
		if(user.getCategory()==0){
			return "packing/ProUseMateOut";
		}else{
			return "commonUsers/ProUseMateOut";
		}*/
		return "packing/ProUseMateOut";
	}

	//使用ajax根据产品分类查找产品型号集合
	@RequestMapping(value="/proUseMateOutCheck.json",method=RequestMethod.POST)
	@ResponseBody
	public List<Product> proUseMateOutCheck(int id){
		return productService.getProByProCate(id);
	}

	//使用ajax根据所选产品显示其信息
	@RequestMapping(value="/showProductInfo.json",method=RequestMethod.POST)
	@ResponseBody
	public Product showProductInfo(int id){
		Product product= productService.getProById(id);
		if(product.getBox()!=null && !product.getBox().equals("")){	//判断此产品是否用此材料
			if(materialsService.getMaterialsNumByName(product.getBox())!=null){	//判断库存中是否有此材料名
				int boxMate=materialsService.getMaterialsNumByName(product.getBox());
				product.setBoxMate(boxMate);
			}else{
				product.setBoxMate(-999);	//设为-999代表库存中无此材料，后续用-999继续实现提示功能
			}
		}else{
			product.setBoxMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}

		if(product.getGasket()!=null && !product.getGasket().equals("")){
			if(materialsService.getMaterialsNumByName(product.getGasket())!=null){
				int gasketMate=materialsService.getMaterialsNumByName(product.getGasket());
				product.setGasketMate(gasketMate);
			}else{
				product.setGasketMate(-999);
			}
		}else{
			product.setGasketMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}	

		if(product.getSpongiaOne()!=null && !product.getSpongiaOne().equals("")){
			if(materialsService.getMaterialsNumByName(product.getSpongiaOne())!=null){
				int spongiaOneMate=materialsService.getMaterialsNumByName(product.getSpongiaOne());
				product.setSpongiaOneMate(spongiaOneMate);
			}else{
				product.setSpongiaOneMate(-999);
			}
		}else{
			product.setSpongiaOneMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}			

		if(product.getSpongiaTwo()!=null && !product.getSpongiaTwo().equals("")){
			if(materialsService.getMaterialsNumByName(product.getSpongiaTwo())!=null){
				int spongiaTwoMate=materialsService.getMaterialsNumByName(product.getSpongiaTwo());
				product.setSpongiaTwoMate(spongiaTwoMate);
			}else{
				product.setSpongiaTwoMate(-999);
			}
		}else{
			product.setSpongiaTwoMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}		

		if(product.getEsdBag()!=null && !product.getEsdBag().equals("")){
			if(materialsService.getMaterialsNumByName(product.getEsdBag())!=null){
				int esdBagMate=materialsService.getMaterialsNumByName(product.getEsdBag());
				product.setEsdBagMate(esdBagMate);
			}else{
				product.setEsdBagMate(-999);
			}
		}else{
			product.setEsdBagMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}	

		if(product.getGeDang()!=null && !product.getGeDang().equals("")){
			if(materialsService.getMaterialsNumByName(product.getGeDang())!=null){
				int geDangMate=materialsService.getMaterialsNumByName(product.getGeDang());
				product.setGeDangMate(geDangMate);
			}else{
				product.setGeDangMate(-999);
			}
		}else{
			product.setGeDangMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}	

		if(product.getEsdTable()!=null && !product.getEsdTable().equals("")){
			if(materialsService.getMaterialsNumByName(product.getEsdTable())!=null){
				int esdTableMate=materialsService.getMaterialsNumByName(product.getEsdTable());
				product.setEsdTableMate(esdTableMate);
			}else{
				product.setEsdTableMate(-999);
			}
		}else{
			product.setEsdTableMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}	

		if(product.getEsdBubbleBag()!=null && !product.getEsdBubbleBag().equals("")){
			if(materialsService.getMaterialsNumByName(product.getEsdBubbleBag())!=null){
				int esdBubbleBagMate=materialsService.getMaterialsNumByName(product.getEsdBubbleBag());
				product.setEsdBubbleBagMate(esdBubbleBagMate);
			}else{
				product.setEsdBubbleBagMate(-999);
			}
		}else{
			product.setEsdBubbleBagMate(-888);	//设为-888不用此材料，后续用-888继续实现提示功能
		}	

		return product;
	}

	//从excel表中批量增加产品包材工艺
	@RequestMapping(value="/addProductFromXls",method=RequestMethod.POST)
	public void addProductFromXls(@RequestParam("file") MultipartFile excel,HttpServletResponse response){
		PrintWriter out=null;
		try {
			out=response.getWriter();
			List<Product> pro=new ArrayList<Product>();
			if(!excel.isEmpty()){//判断文件是否有内容
				List<ProductCategory> productCategory=productCategoryService.getCateList();
				pro=ExcelToSql.getProducts(excel,productCategory);
				//把每条产品的包材信息存入数据库中
				int count=0;//记录上传信息的条数
				for(int i=0;i<pro.size();i++){
					if(pro.get(i).getProductCategoryId()==0){
						out.print("<script>alert('上传失败！文件内容格式有误，请检查\"类别\"项');window.location.href='searchProList';</script>");
					}else{
						//判断上传信息是否已存在数据库中
						if(productService.getPro(pro.get(i).getPwd())<1){
							productService.addProduct(pro.get(i));
							log.info(pro.get(i).getPwd());
							count++;
						}
					}
				}
				out.print("<script>alert('"+count+"条数据上传成功！');window.location.href='searchProList';</script>");
			}else{
				out.print("<script>alert('文件中无内容！上传失败');window.location.href='searchProList';</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			out.print("<script>alert('上传失败！文件内容格式有误，请检查\"类别\"项等');window.location.href='searchProList';</script>");
		}
	}

	//查询所有产品分类
	@RequestMapping(value="/ProCategoryListWindow",method=RequestMethod.GET)
	public String searchProCategoryList(Integer currentPage,Model model){
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		int count=productService.countByProCategory();
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		try {
			List<ProductCategory> ProCategoryList=productService.getProCategoryList((currentPage-1)*maxSize, maxSize);
			model.addAttribute("ProCategoryList",ProCategoryList);
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("pageCount",pageCount);
			model.addAttribute("count",count);
			return "packing/ProCategoryList";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "packing/main";
		}
	}

	//使用ajax新增产品分类
	@ResponseBody
	@RequestMapping(value="/addProCatetory.ajax",method=RequestMethod.POST)
	public Object addProCatetory(HttpServletRequest request){
		String productCategoryName=request.getParameter("productCategoryName").toUpperCase();
		int result=0;
		HashMap<String,String> resultMap=new HashMap<String,String>();
		result=productCategoryService.addCate(productCategoryName);
		if(result==0){
			resultMap.put("addresult", "empty");
		}else if(result==-1){
			resultMap.put("addresult", "exsit");
		}else if(result==1){
			resultMap.put("addresult", "true");
		}
		return resultMap;
	}

	//根据id修改产品分类
	@ResponseBody
	@RequestMapping(value="/updateProCategory.ajax",method=RequestMethod.POST)
	public Object updateProCategory(int id,Model model){
		Map<String,String> resultMap=new HashMap<String,String>();
		if(id==0){
			resultMap.put("updateResult", "empty");//入参为0
		}else{
			if(productService.getProByProCate(id)!=null){
				resultMap.put("updateResult", "exsit");//分类下有数据
			}else{
				productCategoryService.updataProCategory(id);
				resultMap.put("updateResult", "true");//分类下无数据可以修改分类名称
			}
		}
		return resultMap;
	}

	//根据id删除产品分类
	@ResponseBody
	@RequestMapping(value="/delProCategory.ajax",method=RequestMethod.GET)
	public Object delProCategory(int id,Model model){
		Map<String,String> resultMap=new HashMap<String,String>();
		if(id==0){
			resultMap.put("delResult", "empty");//入参为0
		}else{
			if(productService.getProByProCate(id).size()!=0){
				resultMap.put("delResult", "exsit");//分类下有数据不可删除此分类
			}else{
				productCategoryService.delProCategory(id);
				resultMap.put("delResult", "true");//分类下无数据可以删除分类
			}
		}
		return resultMap;
	}
}

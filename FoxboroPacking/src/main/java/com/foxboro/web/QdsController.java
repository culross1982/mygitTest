package com.foxboro.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxboro.entity.Page;
import com.foxboro.entity.QdsErrorCode;
import com.foxboro.entity.QdsInspectTotal;
import com.foxboro.entity.QdsModule;
import com.foxboro.entity.QdsProduct;
import com.foxboro.entity.QdsProductAssy;
import com.foxboro.entity.QdsProductError;
import com.foxboro.entity.QdsProductOrder;
import com.foxboro.entity.QdsProductTest;
import com.foxboro.entity.Users;
import com.foxboro.service.qds.ErrorCodeService;
import com.foxboro.service.qds.ModuleService;
import com.foxboro.service.qds.ProductAssyService;
import com.foxboro.service.qds.ProductErrorService;
import com.foxboro.service.qds.ProductOrderService;
import com.foxboro.service.qds.ProductTestService;
import com.foxboro.service.qds.QProductService;
import com.foxboro.tools.Constants;

@Controller
@RequestMapping(value="/qds")
public class QdsController {
	private Logger log=Logger.getLogger(QdsController.class);
	@Autowired
	private ProductAssyService qdsProAssySer;
	@Autowired
	private ProductTestService qdsProTestSer;
	@Autowired
	private ModuleService moduleSer;
	@Autowired
	private ErrorCodeService errorCodeService;
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private QProductService qProductService;
	@Autowired
	private ProductErrorService productErrorService;

	private int qdsProCategoryId=1;	//1表示产品分类为din
	private String qdsProCategoryName="DIN";

	//按条件分页查询QDS装配产品
	@RequestMapping("/dinAssyWindows")
	public String dinAssyWindows(HttpServletRequest request,Model model,Integer currentPage){
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
		//分页查询
		String moduleNo=request.getParameter("moduleNo");
		String assyNo=request.getParameter("assyNo");
		String realname=request.getParameter("realname");
		if(realname!=null){
			try {
				realname=new String(realname.getBytes("8859_1"),"utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProductAssy> qdsProductAssyList=new ArrayList<QdsProductAssy>();
		QdsProductAssy qProAssy=new QdsProductAssy();
		try {
			int maxSize=Constants.maxSize;
			qProAssy.setModuleNo(moduleNo);
			qProAssy.setAssyNo(assyNo);
			qProAssy.setRealname(realname);
			qProAssy.setDateStart(dateStart);
			qProAssy.setDateEnd(dateEnd);
			qProAssy.setBeginNo((currentPage-1)*maxSize);
			qProAssy.setPageSize(maxSize);
			qProAssy.setQdsProCategoryName(qdsProCategoryName);
			int count = qdsProAssySer.getQProAssyCount(qProAssy);	//装配数据总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductAssyList=qdsProAssySer.getQProAssy(qProAssy);	//装配数据清单
			model.addAttribute("qdsProductAssyList",qdsProductAssyList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qProAssy",qProAssy);	//回显
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinAssy";
	}

	//进行包材历史清单页面展示asc
	@RequestMapping(value="/dinDateAsc",method=RequestMethod.GET)
	public String dinDateAsc(Integer currentPage,Model model){
		//分页查询
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProductAssy> qdsProductAssyList=new ArrayList<QdsProductAssy>();
		QdsProductAssy qProAssy=new QdsProductAssy();
		try {	
			int maxSize=Constants.maxSize;
			qProAssy.setBeginNo((currentPage-1)*maxSize);
			qProAssy.setPageSize(maxSize);
			qProAssy.setQdsProCategoryName(qdsProCategoryName);
			int count=qdsProAssySer.getQProAssyCount(qProAssy);	//装配数据总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductAssyList=qdsProAssySer.getQProAssyAsc(qProAssy);	//装配数据清单
			model.addAttribute("qdsProductAssyList",qdsProductAssyList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinAssy";
	}

	//新增模块串号检查
	@RequestMapping(value="/dinAddModuleNo.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addPartNo(String moduleNo,int qdsProCategoryId){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(moduleNo==null || moduleNo.equals("")){
				result.put("data", "empty") ;	//输入为空
			}else if(moduleNo.length()!=19){
				result.put("data", "lengthError") ;	//长度错误
			}else{
				if(moduleNo.indexOf("o")>-1 || moduleNo.indexOf("i")>-1){
					result.put("data", "incorrect");	//数据不合格
				}else{
					QdsProductAssy qProAssy=new QdsProductAssy();
					qProAssy.setModuleNo(moduleNo.toUpperCase());	//输入信息转大写再上传
					Integer isExsitProduct=qProductService.isExsitProductByModuleNo(moduleNo, qdsProCategoryId);
					if(isExsitProduct!=0){	//工作令中有此SN
						int count=qdsProAssySer.getAssyDataIsExsit(qProAssy);
						if(count==2){	//装配数据已存在！
							result.put("data", "exsit");
						}else if(count==1){	//已有部分装配数据！
							String assyNo=qdsProAssySer.getAssyNoByModuleNo(moduleNo,qdsProCategoryId);
							result.put("data", assyNo);
						}else if(count==0){	//数据合格，可以添加
							result.put("data", "success");	
						}else{
							result.put("data", "moreCount");	
						}
					}else{	//工作令中无此SN
						result.put("data", "noexsitSn");
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("data","failed") ;	//操作失败
		}
		return result;
	}

	//新增单板串号检查
	@RequestMapping(value="/dinAddAssyNo.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addAssyNo(String assyNo,int qdsProCategoryId,String moduleNo){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(assyNo==null || assyNo.equals("")){
				result.put("data", "empty") ;	//输入为空
			}else if(assyNo.length()!=12){
				result.put("data", "lengthError") ;	//长度错误
			}else{
				String part=assyNo.substring(0, 8);
				//从基础数据获取单板串号对应的模块串号及其版本
				QdsModule qdsModule=moduleSer.getModuleByPart(part, qdsProCategoryId);
				if(qdsModule!=null){	//有对应的基础数据
					String moduleNoDatabase=qdsModule.getModule()+qdsModule.getVer();
					if(moduleNoDatabase.equals(moduleNo.substring(0, 9))){ //基础数据和输入数据比较是否相同
						QdsProductAssy qProAssy=new QdsProductAssy();
						qProAssy.setAssyNo(assyNo.toUpperCase());
						int count=qdsProAssySer.getAssyDataIsExsit(qProAssy);
						if(count!=0){
							result.put("data", "exsit");	//数据已存在
						}else{
							result.put("data", "success");	//数据合格，可以添加
						}
					}else{
						result.put("data", "databaseError");	//check基础数据错误
					}
				}else{
					result.put("data", "databaseEmpty");	//无对应的基础数据
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("data","failed") ;	//操作失败
		}
		return result;
	}

	//新增装配数据
	@RequestMapping(value="/dinDoAddAssy.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object dinDoAddAssy(String assy,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		if(assy!=null && assy!=""){
			try {
				QdsProductAssy qdsProductAssy=new QdsProductAssy();
				Users currentUser=(Users) session.getAttribute("user");
				qdsProductAssy.setAssyBy(currentUser.getId());
				JSONObject assyObject=JSON.parseObject(assy);
				String moduleNo=assyObject.getString("moduleNo");
				String assyNoP=assyObject.getString("assyNoP");
				qdsProductAssy.setModuleNo(moduleNo.toUpperCase());
				qdsProductAssy.setQdsProCategoryId(qdsProCategoryId); 
				if(assyNoP!=null){	//已有部分装配数据
					qdsProductAssy.setAssyNo(assyNoP.toUpperCase());
					qdsProAssySer.addAssyData(qdsProductAssy);	//新增P板数据	
				}
				String assyNoM=assyObject.getString("assyNoM");
				qdsProductAssy.setAssyNo(assyNoM.toUpperCase());
				qdsProAssySer.addAssyData(qdsProductAssy);	//新增M板数据
				int assyStatus=1; //1为装配数据已PASS
				qProductService.updateAssyStatusQdsProductByModuleNo(moduleNo, assyStatus);	//改变QdsPruduct的assyStatus
				result.put("result", "success");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				result.put("result", "failed");
			}
		}else{
			result.put("result", "error");
		} 
		return result;
	}

	//按id删除装配数据
	@RequestMapping(value="/delAssyDataById.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object delAssyDataById(int id,String moduleNo,int qdsProCategoryId){
		Map<String,String> result=new HashMap<String,String>();
		if(moduleNo!=null){
			try {
				//查询要删除的装配数据是否存在测试数据
				Integer isExistTest = qdsProTestSer.isExistTestByModuleNo(moduleNo, qdsProCategoryId);
				if(isExistTest==0){	//不存在测试数据，可删除
					try {
						qdsProAssySer.delAssyDataById(id);
						int assyStatus=0; //0为装配数据fail
						qProductService.updateAssyStatusQdsProductByModuleNo(moduleNo, assyStatus);	//改变QdsPruduct的assyStatus
						result.put("result", "success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						result.put("result", "failed");
					}
				}else{	//存在测试数据，不可删除
					result.put("result", "isExistTest");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			result.put("result", "error");
		}
		return result;
	}

	//修改装配数据
	@RequestMapping(value="/modifyProductAssy.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyProductAssy(@RequestParam String assyModify,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		if(assyModify!=null && assyModify!=""){
			try {
				JSONObject assyModifyObject=JSON.parseObject(assyModify);	//转换json
				String partNoModify=assyModifyObject.getString("moduleNoModify");
				String assyNoModify=assyModifyObject.getString("assyNoModify");
				int id=assyModifyObject.getInteger("id");
				Integer isExistTest=qdsProTestSer.isExistTestByModuleNo(partNoModify, qdsProCategoryId);
				if(isExistTest==0){
					QdsProductAssy qProAssy=new QdsProductAssy();
					qProAssy.setModuleNo(partNoModify.toUpperCase());
					qProAssy.setAssyNo(assyNoModify.toUpperCase());
					qProAssy.setId(id);
					Users user=(Users) session.getAttribute("user");
					qProAssy.setAssyModifyBy(user.getId());
					qdsProAssySer.updateProductAssy(qProAssy);
					result.put("result", "success");
				}else{
					result.put("result", "isExistTest");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.put("result", "failed");
			}
		}else{
			result.put("result", "error");
		} 
		return result;
	}

	//按条件分页查询QDS测试产品
	@RequestMapping(value="/dinTestWindows",method=RequestMethod.GET)
	public String dinTestWindows(HttpServletRequest request,Model model,Integer currentPage){
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
		//分页查询
		String moduleNo=request.getParameter("moduleNo");
		String realname=request.getParameter("realname");
		if(realname!=null){
			try {
				realname=new String(realname.getBytes("8859_1"),"utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProductTest> qdsProductTestList=new ArrayList<QdsProductTest>();
		QdsProductTest qProTest=new QdsProductTest();
		try {
			int maxSize=Constants.maxSize;
			qProTest.setModuleNo(moduleNo);
			qProTest.setRealname(realname);
			qProTest.setDateStart(dateStart);
			qProTest.setDateEnd(dateEnd);
			qProTest.setBeginNo((currentPage-1)*maxSize);
			qProTest.setPageSize(maxSize);
			qProTest.setQdsProCategoryId(1);	//1为DIN
			int count = qdsProTestSer.getQProTestCount(qProTest);	//测试数据总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductTestList=qdsProTestSer.getQProTest(qProTest);	//测试数据清单
		/*	for (QdsProductTest qdsProductTest : qdsProductTestList) {
				log.debug("qdsProductTest.getRemark()============"+qdsProductTest.getRemark());
			}*/
			model.addAttribute("qdsProductTestList",qdsProductTestList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qProTest",qProTest);	//回显
			List<QdsErrorCode> qdsErrorCodeList=new ArrayList<QdsErrorCode>();
			qdsErrorCodeList=errorCodeService.getAllErrorCode();	//获取所有维修代码
			model.addAttribute("qdsErrorCodeList",qdsErrorCodeList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinTest";
	}

	//从.log文件批量上传DIN测试数据
	@RequestMapping(value="/addDinTestFromLog",method=RequestMethod.POST)
	public void addDinTestFromLog(MultipartFile attachment,HttpSession session,HttpServletResponse response){
		try {
			PrintWriter out=response.getWriter();
			//Map<String,String> result=new HashMap<String,String>();
			if(!attachment.isEmpty()){
				String url=attachment.getOriginalFilename();	//原文件名
				String suffix=url.substring(url.lastIndexOf(".")).toUpperCase();	//获取文件后缀
				if(suffix.equals(".LOG")){
					String tempFileName=url;
					File targetFile=new File("d:\\",tempFileName);
					try {
						attachment.transferTo(targetFile);
						File file=new File("d:\\"+tempFileName);
						Users currentUser=(Users) session.getAttribute("user");
						HashMap<String,String> updateResultList=attachmentToSave(file,currentUser.getId());	//调用上传方法
						String uploadFailed="";
						String uploadCount="";
						for(String updateResult:updateResultList.values()){
							if(updateResult.length()==19){
								uploadFailed=uploadFailed+updateResult+" | ";	//未成功上传的集合
							}else{
								uploadCount=updateResult;	//成功上传的条数
							}
						}
						out.print("<script>alert('"+uploadCount+"条测试数据上传成功！"+uploadFailed+"无装配数据，上传失败！');window.location.href='dinTestWindows';</script>");
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					//result.put("result", "notLOG");	//扩展名不正确
					out.print("<script>alert('扩展名不正确！请选择正确的文件');window.location.href='dinTestWindows';</script>");
				}
			}else{
				//result.put("result", "empty");	//文件为空
				out.print("<script>alert('文件为空！请选择正确的文件');window.location.href='dinTestWindows';</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return result;
	}

	//本地文件信息上传至SQL（DIN）
	protected HashMap<String,String> attachmentToSave(File file,int testBy){
		//int result=0;
		HashMap<String,String> resultMap=new HashMap<String,String>();
		int resultCount=0;	//上传的成功条数
		FileReader fr=null;
		BufferedReader br=null;
		//List<QdsProductTest> recoredList=new ArrayList<QdsProductTest>();
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String sb=new String();
			QdsProductTest recored=new QdsProductTest();
			String moduleNo=null;	//存moduleNo
			List<String> moduleNoList=new ArrayList<String>();	//存放moduleNo集合，用于：更新qdsProduct中testStatus的方法使用
			int j=0;	//定义HashMap的key值
			while((sb=br.readLine())!=null){	//获取每行信息
				sb=sb.replace(" ", "");	//去掉所有空格
				if(sb!=null && !sb.equals("")){
					String[] str=sb.split("\\|");
					for (int i=0;i<str.length;i++) {
						moduleNo=str[0]+str[1]+str[2];
						recored.setModuleNo(moduleNo);
						recored.setTestCode(str[3]);
						recored.setTestStatus(str[9]);
						recored.setStatus(str[4]);
						recored.setTestResult(str[8]);
						String time=str[6]+" "+str[7];
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
						recored.setTestTime(sdf.parse(time));
						recored.setTestBy(testBy);
						recored.setTestEquipment(str[5]);
						recored.setTestDiscription(str[10]);
						recored.setErrorStatus(0);
					}
					int qdsProCategoryId=1;	//1为DIN
					recored.setQdsProCategoryId(qdsProCategoryId);
					Integer assyStatus;
					try {
						assyStatus=qProductService.getAssyStatusByModuleNo(moduleNo, qdsProCategoryId);//装配数据是否PASS
						if(assyStatus==1){	//装配数据PASS
							try {
								qdsProTestSer.addTestData(recored);	//将每条数据上传到SQL中
								moduleNoList.add(moduleNo);//将每条数据放入集合中，用于：更新qdsProduct中testStatus的方法使用
								resultCount++;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{	//装配数据FAIL
							j++;	//HashMap的key值
							resultMap.put(String.valueOf(j), moduleNo);	//存入装配数据FAIL的值
							//resultList.add(moduleNo);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						j++;	//HashMap的key值
						resultMap.put(String.valueOf(j), moduleNo);	//存入未生成SN的moduleNo
					}
				}
			}
			resultMap.put("resultCount",String.valueOf(resultCount));	//上传的成功条数存入结果集中
			updateQdsProTestStatus(moduleNoList);	//调用更新qdsProduct中testStatus的方法
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	//test数据上传后更新qdsProduct中testStatus的方法（DIN）
	protected void updateQdsProTestStatus(List<String> moduleNoList){
		try {
			for(int i=0;i<moduleNoList.size();i++){
				String moduleNo=moduleNoList.get(i);
				int qdsProCategoryId=1;	//产品分类1表示DIN
				//获取qdsProductTest最后一条记录的testResult的状态
				String testResult=qdsProTestSer.isTestPassAtTheEnd(moduleNo, qdsProCategoryId);	
				if(testResult.equals("P")){	//状态为"P"则设置qdsProduct的testStatus为1
					int testStatus=1;
					//设置qdsProduct的testStatus为1
					qProductService.updateTestStatusQdsProductByModuleNo(moduleNo, testStatus);
					//根据moduleNo查询PASS的产品是否有FAIL记录
					Integer isTestFailed=qdsProTestSer.isTestFailedByModuleNo(moduleNo, qdsProCategoryId);
					if(isTestFailed!=0){	//有FAIL记录则errorStatus设为1，表示有维修数据需要待输入
						qdsProTestSer.updateErrorStatusAtTheEnd(moduleNo, qdsProCategoryId);
					}
				}else if(testResult.equals("F")){	//状态为"F"则设置qdsProduct的testStatus为0
					int testStatus=0;
					qProductService.updateTestStatusQdsProductByModuleNo(moduleNo, testStatus);//设置qdsProduct的testStatus为0
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//输入维修数据按“提交”按钮：新增产品不良记录
	@RequestMapping(value="/addProductError.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addProductError(String moduleNo,int qdsProCategoryId,int errorCodeId,
								  String remark,int productTestId,HttpSession session){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		try {
			QdsProductError qdsProductError=new QdsProductError();
			qdsProductError.setModuleNo(moduleNo);
			qdsProductError.setErrorCodeId(errorCodeId);
			//去掉用户输入备注中的空格，因为页面无法显示空格后内容
			qdsProductError.setRemark(remark.replace(" ", "").toUpperCase());	
			qdsProductError.setQdsProCategoryId(qdsProCategoryId);
			qdsProductError.setQdsProductTestId(productTestId);
			Users currentUser=(Users) session.getAttribute("user");
			qdsProductError.setRepairBy(currentUser.getId());
			productErrorService.addProductError(qdsProductError);	//在SQL中新增产品不良记录
			int errorStatus=2;	//2表示维修数据已输入
			qdsProTestSer.updateProductTestById(productTestId, errorStatus, qdsProCategoryId);//更改productTest中的不良状态
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("result", "failed");
		}
		return resultMap;
	}
	
	//修改界面按“修改”按钮
	@RequestMapping(value="/modifyProductError.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyProductError(String moduleNo1,int qdsProCategoryId,int errorCodeId1,
								  String remark1,int productTestId1,HttpSession session){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		try {
			QdsProductError qdsProductError=new QdsProductError();
			qdsProductError.setModuleNo(moduleNo1);
			qdsProductError.setErrorCodeId(errorCodeId1);
			//去掉用户输入备注中的空格，因为页面无法显示空格后内容
			qdsProductError.setRemark(remark1.replace(" ", "").toUpperCase());	
			qdsProductError.setQdsProCategoryId(qdsProCategoryId);
			qdsProductError.setQdsProductTestId(productTestId1);
			productErrorService.updateProductError(qdsProductError);//在SQL中修改产品不良记录
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("result", "failed");
		}
		return resultMap;
	}

	//QDS产品检验界面（DIN）
	@RequestMapping(value="/dinInspectWindows",method=RequestMethod.GET)
	public String dinInspectWindows(Model model,HttpServletRequest request,Integer currentPage,HttpSession session){
		//分页查询
		String moduleNo=request.getParameter("moduleNo");
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProduct> qdsProductList=new ArrayList<QdsProduct>();
		QdsProduct qdsProduct=new QdsProduct();
		try {
			
			/*显示左边待检验列表*/
			int maxSize=Constants.maxSize;
			qdsProduct.setModuleNo(moduleNo);
			qdsProduct.setBeginNo((currentPage-1)*maxSize);
			qdsProduct.setPageSize(maxSize);
			int assyStatus=1;	//装配状态PASS
			qdsProduct.setAssyStatus(assyStatus);
			int testStatus=1;	//测试状态PASS
			qdsProduct.setTestStatus(testStatus);
			int qdsProCategoryId=1; //1为DIN
			qdsProduct.setQdsProCategoryId(qdsProCategoryId);
			int count = qProductService.getQdsProductCountByInspect(qdsProduct);	//QDS中DIN的产品总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductList=qProductService.getQdsProductByInspect(qdsProduct);	//QDS中DIN的产品清单
			model.addAttribute("qdsProductList",qdsProductList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qdsProduct",qdsProduct);	//回显
			
			/*显示右边“今日检验统计”*/
			QdsProduct qdsProductTotal=new QdsProduct();
			qdsProductTotal.setQdsProCategoryId(qdsProCategoryId);
			Users currentUser=(Users) session.getAttribute("user");
			qdsProductTotal.setInspectionBy(currentUser.getId());
			//获取当天已检验的所有orderId
			List<Integer> qdsOrderIdList=qProductService.getQdsProOrderIdByInspectionTime(qdsProductTotal);
			QdsProductOrder qdsProductOrder=new QdsProductOrder();
			List<QdsInspectTotal> qdsInspectTotalList=new ArrayList<QdsInspectTotal>();//存放今日检验统计结果
			for(int i=0;i<qdsOrderIdList.size();i++){
				int id=qdsOrderIdList.get(i);
				qdsProductOrder=productOrderService.getQdsProOrderById(id, qdsProCategoryId);
				String order=qdsProductOrder.getOrder();	//获取工作令
				qdsProductTotal.setQdsProductOrderId(id);//设置要查询的orderId
				int countOrder=qProductService.getCountByQdsProOrderId(qdsProductTotal);//获取工作令的产品数量
				QdsInspectTotal qdsInspectTotal=new QdsInspectTotal();//存放今日检验统计结果
				qdsInspectTotal.setOrder(order);
				qdsInspectTotal.setCount(countOrder);
				qdsInspectTotalList.add(qdsInspectTotal);
			}
			model.addAttribute("qdsInspectTotalList", qdsInspectTotalList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinInspect";
	}
	
	//检验界面的“模块串号”验证
	@RequestMapping(value="/inspectCheckModuleNo.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object inspectCheckModuleNo(String moduleNo,int qdsProCategoryId){
		Map<String,String> result=new HashMap<String,String>();
		if(moduleNo==null || moduleNo.equals("")){
			result.put("result", "empty");	//输入为空
		}else if(moduleNo.length()!=19){
			result.put("result", "lengthError");	//长度错误
		}else{
			try {
				QdsProduct qdsProduct=new QdsProduct();
				qdsProduct=qProductService.getQdsProductByModuleNo(moduleNo, qdsProCategoryId);
				if(qdsProduct.getAssyStatus()==1 && qdsProduct.getTestStatus()==1){//存在测试数据，可以检验
					if(qdsProduct.getInspectionStatus()==0){//待检验
						result.put("result", "success");
					}else{	//已检验过，无需再次检验
						result.put("result", "isExistInspect");
					}
						
				}else{	//不存在测试数据，无法检验
					result.put("result", "noExistTest");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("result", "failed");	
			}
		}
		return result;
	}
	
	//新增检验数据
	@RequestMapping(value="/addInspect.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addInspect(String checkModuleNo,int qdsProCategoryId,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		try {
			QdsProduct qdsProduct=new QdsProduct();
			qdsProduct.setInspectionStatus(1); //1表示检验合格
			qdsProduct.setModuleNo(checkModuleNo.toUpperCase());
			qdsProduct.setQdsProCategoryId(qdsProCategoryId);
			Users currentUser=(Users) session.getAttribute("user");
			qdsProduct.setInspectionBy(currentUser.getId());
			qProductService.updateQdsProInsByModuleNo(qdsProduct);//更新qdsProduct中inspect
			/*QdsProduct currentQdsProduct=new QdsProduct();
			//根据moduleNo获取新增数据的qdsProductOrderId
			currentQdsProduct=qProductService.getQdsProductByModuleNo(checkModuleNo, qdsProCategoryId);
			//获取新增数据对应的工作令
			QdsProductOrder qdsProductOrder=new QdsProductOrder();
			qdsProductOrder=productOrderService.getQdsProOrderById(currentQdsProduct.getQdsProductOrderId(), qdsProCategoryId);
			log.debug("qdsProductOrder.getOrder()=========="+qdsProductOrder.getOrder());*/
			result.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}
	
	//撤销已检验数据
	@RequestMapping(value="/cancelInspect.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object cancelInspect(String moduleNo,int qdsProCategoryId,int id){
		Map<String,String> result=new HashMap<String,String>();
		try {
			QdsProduct qdsProduct=new QdsProduct();
			qdsProduct.setId(id);
			qdsProduct.setQdsProCategoryId(qdsProCategoryId);
			qdsProduct.setInspectionStatus(0); //1表示未检验
			qProductService.updateQdsProInsByModuleNo(qdsProduct);
			result.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//DIN基础数据界面
	@RequestMapping(value="/dinDatabaseWindows",method=RequestMethod.GET)
	public String dinDatabaseWindows(Model model,HttpServletRequest request,Integer currentPage){
		//分页查询
		String module=request.getParameter("module");
		String part=request.getParameter("part");
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsModule> qdsModuleList=new ArrayList<QdsModule>();
		QdsModule qdsModule=new QdsModule();
		try {
			int maxSize=Constants.maxSize;
			qdsModule.setModule(module);
			qdsModule.setPart(part);
			qdsModule.setBeginNo((currentPage-1)*maxSize);
			qdsModule.setPageSize(maxSize);
			qdsModule.setQdsProCategoryName(qdsProCategoryName);
			int count = moduleSer.getQProModuleCount(qdsModule);	//基础数据总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsModuleList=moduleSer.getQProModule(qdsModule);	//基础数据清单
			model.addAttribute("qdsModuleList",qdsModuleList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qdsModule",qdsModule);	//回显
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinDatabase";
	}

	//新增基础数据
	@RequestMapping(value="/addDatabase.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addDatebase(HttpServletRequest request,String module,String ver,
			String part,String category){
		Map<String,String> result=new HashMap<String,String>();
		try {
			QdsModule qdsModule=new QdsModule();
			qdsModule.setModule(module.toUpperCase());
			qdsModule.setVer(ver.toUpperCase());
			qdsModule.setPart(part.toUpperCase());
			qdsModule.setCategory(category);
			Users user=(Users) request.getSession().getAttribute("user");
			qdsModule.setCreateBy(user.getId());
			qdsModule.setQdsProCategoryId(qdsProCategoryId);
			moduleSer.addQModule(qdsModule);
			result.put("result", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//新增基础数据的模块验证
	@RequestMapping(value="/dinAddModule.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object dinAddModule(String module,String part){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(module!=null && !module.trim().equals("")){
				QdsModule qdsModule=new QdsModule();
				qdsModule.setModule(module.trim());	//取消空格
				qdsModule.setQdsProCategoryName(qdsProCategoryName);
				if(moduleSer.searchModule(qdsModule)!=null){ //已存在模块型号则警告
					QdsModule m=moduleSer.searchModule(qdsModule);
					result.put("result", m.getVer());
				}else{
					result.put("result", "success");
				}
			}else if(part!=null && !part.trim().equals("")){
				QdsModule qdsModule=new QdsModule();
				qdsModule.setPart(part.trim());	//取消空格
				qdsModule.setQdsProCategoryName(qdsProCategoryName);
				//if(moduleSer.searchModule(qdsModule)!=null){ //已存在单板型号则报错提示		???
				//	result.put("result", "exsit");
				//}else{
					result.put("result", "success");
				//}
			}else{
				result.put("result", "empty");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//删除基础数据
	@RequestMapping(value="/delDatabase.ajax",method=RequestMethod.GET)
	@ResponseBody
	public Object delDatabase(int id){
		Map<String,String> result=new HashMap<String,String>();
		if(id!=0){
			try {
				moduleSer.delModule(id);
				result.put("result", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("result", "failed");
			}
		}else{
			result.put("result", "noexist");
		}
		return result;
	}

	//维修代码界面
	@RequestMapping(value="/errorWindows",method=RequestMethod.GET)
	public String errorWindows(Model model){
		List<QdsErrorCode> qdsErrorCodeList;
		try {
			qdsErrorCodeList = errorCodeService.getAllErrorCode();
			model.addAttribute("qdsErrorCodeList",qdsErrorCodeList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qErrorCode";
	}

	//S/N自动生成界面
	@RequestMapping(value="/dinCreateSnWindows",method=RequestMethod.GET)
	public String dinCreateWindows(Model model,HttpServletRequest request,Integer currentPage){
		//分页查询
		String order=request.getParameter("order");
		String module=request.getParameter("module");
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProductOrder> qdsProductOrderList=new ArrayList<QdsProductOrder>();
		QdsProductOrder qdsProductOrder=new QdsProductOrder();
		try {
			int maxSize=Constants.maxSize;
			qdsProductOrder.setOrder(order);
			qdsProductOrder.setModule(module);
			qdsProductOrder.setBeginNo((currentPage-1)*maxSize);
			qdsProductOrder.setPageSize(maxSize);
			qdsProductOrder.setQdsProCategoryName(qdsProCategoryName);
			int count = productOrderService.getQdsProductOrderCount(qdsProductOrder);	//工作令总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductOrderList=productOrderService.getQdsProductOrder(qdsProductOrder);	//工作令清单
			model.addAttribute("qdsProductOrderList",qdsProductOrderList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qdsProductOrder",qdsProductOrder);	//回显
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinCreateSn";
	}

	//S/N生成的工作令验证
	@RequestMapping(value="/addOrder.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object addOrder(String order){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(order!=null && !order.trim().equals("")){
				if(order.length()!=12){
					result.put("result", "lengthError") ;	//长度错误
				}else{
					QdsProductOrder qdsProductOrder=new QdsProductOrder();
					qdsProductOrder.setOrder(order.trim());	//取消空格
					qdsProductOrder.setQdsProCategoryName(qdsProCategoryName);
					if(productOrderService.getQdsProductOrderCount(qdsProductOrder)!=0){ //已存在模块型号则报错
						result.put("result", "exsit");
					}else{
						result.put("result", "success");
					}
				}
			}else{
				result.put("result", "empty");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//S/N生成的模块型号验证
	@RequestMapping(value="/checkModule.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object checkModule(String module,String qdsProCategoryName){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(module!=null && !module.trim().equals("")){
				QdsModule qdsModule=new QdsModule();
				qdsModule.setModule(module.trim());
				qdsModule.setQdsProCategoryName(qdsProCategoryName);
				qdsModule=moduleSer.searchModule(qdsModule);
				if(qdsModule==null){
					result.put("result", "noexsit");
				}else{
					result.put("result", "success");
				}
			}else{
				result.put("result", "empty");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}	
		return result;
	}

	//S/N生成的版本号验证
	@RequestMapping(value="/checkVer.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object checkVer(String ver,String module,String qdsProCategoryName){
		Map<String,String> result=new HashMap<String,String>();
		try {
			if(ver!=null && !ver.trim().equals("")){
				if(ver.length()!=2){
					result.put("result", "lengthError") ;	//长度错误
				}else{
					String baseVer=moduleSer.getVerByModule(module.trim(), qdsProCategoryName);	//通过module获取基础数据中对应的ver
					if(ver.trim().toUpperCase().equals(baseVer)){ //基础数据中无对应的版本
						result.put("result", "success");
					}else{
						result.put("result", "noexsit");
					}
				}
			}else{
				result.put("result", "empty");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//S/N生成预览
	@RequestMapping(value="/createSnReview.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object createSnReview(int pwdQuantity,String module,String order,String qdsProCategoryName){
		Map<String,String> result=new HashMap<String,String>();
		try {
			String ver = moduleSer.getVerByModule(module,qdsProCategoryName); //获取版本
			String week="";
			//获取周期
			Calendar c=Calendar.getInstance();
			int w = c.get(Calendar.WEEK_OF_YEAR);
			int year = c.get(Calendar.YEAR);
			if(w<10){
				week=String.valueOf(year).substring(2)+"0"+w;
			}else{
				week=String.valueOf(year).substring(2)+w;
			}
			String sn=module.toUpperCase()+ver+"SF"+week;	//模块（转大写）+版本+周期
			List<QdsProduct> qdsProductList=new ArrayList<QdsProduct>();
			//Integer isExsitP=qProductService.isExsitProductByModuleNo(sn, qdsProCategoryName);	//本周期型号是否存在
			String snEnd=productOrderService.getProductOrderBySnEnd(sn, qdsProCategoryName);	//本周期型号是否存在
			if(snEnd==null){	//本周期无此型号产品
				int count=1;
				for(int i=0;i<pwdQuantity;i++){
					QdsProduct qdsProduct=new QdsProduct();
					if(count<10){
						qdsProduct.setModuleNo(sn+"000"+count);
					}else if(10<=count && count<100){
						qdsProduct.setModuleNo(sn+"00"+count);
					}else if(100<=100 && count<1000){
						qdsProduct.setModuleNo(sn+"0"+count);
					}else{
						qdsProduct.setModuleNo(sn+count);
					}
					qdsProductList.add(qdsProduct);
					count++;
				}
			}else{	//本周期有此型号产品
				int count=Integer.parseInt(snEnd.substring(15));	//获取本周期内的串号最大值
				for(int i=0;i<pwdQuantity;i++){
					QdsProduct qdsProduct=new QdsProduct();
					count++;	//本次串号的起始值
					if(count<10){
						qdsProduct.setModuleNo(sn+"000"+count);
					}else if(10<=count && count<100){
						qdsProduct.setModuleNo(sn+"00"+count);
					}else if(100<=100 && count<1000){
						qdsProduct.setModuleNo(sn+"0"+count);
					}else{
						qdsProduct.setModuleNo(sn+count);
					}
					qdsProductList.add(qdsProduct);
				}
			}
			String fastjson=JSON.toJSONString(qdsProductList);
			result.put("result", fastjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}

	//S/N生成(使用synchronized，未进行多用户测试？？？)
	@RequestMapping(value="/createSn.ajax",method=RequestMethod.POST)
	@ResponseBody
	public synchronized Object createSn(String reviewList,String order,String module1,String ver,Integer pwdQuantity,
			Integer qdsProCategoryId,HttpSession session){
		Map<String,String> result=new HashMap<String,String>();
		if(reviewList!=null && !reviewList.equals("")){
			Users currentUser=(Users) session.getAttribute("user");
			JSONObject reviewListObject=JSON.parseObject(reviewList);	//转换json
			//将数据添加到qdsProductOrder中
			QdsProductOrder qdsProductOrder=new QdsProductOrder();
			qdsProductOrder.setOrder(order.toUpperCase());
			qdsProductOrder.setModule(module1.toUpperCase());
			qdsProductOrder.setVer(ver.toUpperCase());
			qdsProductOrder.setPwdQuantity(pwdQuantity);
			qdsProductOrder.setCreateBy(currentUser.getId());
			qdsProductOrder.setQdsProCategoryId(qdsProCategoryId);
			String snStart=reviewListObject.getString(String.valueOf("0"));	//从jsp获取模块串号的起始位
			qdsProductOrder.setSnStart(snStart);
			String snEnd=reviewListObject.getString(String.valueOf(reviewListObject.size()-1)); //从jsp获取模块串号的结束位
			qdsProductOrder.setSnEnd(snEnd);
			try {
				productOrderService.addProductOrder(qdsProductOrder);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				result.put("result", "orderFailed");
			}
			try {
				for(int i=0;i<reviewListObject.size();i++){
					//从qdsProductOrder中获取对应的id
					int orderId=productOrderService.getProductOrderIdByOrder(order);
					//将数据添加到qdsProcduct中
					QdsProduct qdsProduct=new QdsProduct();
					String moduleNo=reviewListObject.getString(String.valueOf(i));	//从jsp获取模块串号
					qdsProduct.setModuleNo(moduleNo);	//模块串号
					qdsProduct.setQdsProductOrderId(orderId); //产品工作令id
					qdsProduct.setQdsProCategoryId(qdsProCategoryId);	//产品分类:1为DIN
					qProductService.addProduct(qdsProduct);
				}
				result.put("result", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("result", "productFailed");
			}
		}else{
			result.put("result", "empty");
		}
		return result;
	}

	//删除product
	@RequestMapping(value="/deleteProductOrder.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteProductOrder(Integer id,int qdsProCategoryId){
		Map<String,String> result=new HashMap<String,String>();
		boolean flag=true;	//工作令否已有装配数据的标志位
		if(id!=0 && qdsProCategoryId!=0){
			List<Integer> assyStatusList;
			try {
				//按id查看是此工作令否已有装配数据
				assyStatusList = qProductService.getAssyStatusById(id, qdsProCategoryId);
				for (Integer assyStatus : assyStatusList) {
					if(assyStatus==1){
						flag=false;
						break;
					}
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(flag==true){
				try {
					qProductService.delProduct(id);	//删除product
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					result.put("result", "productFailed");
				}
				try {
					productOrderService.delProductOrderById(id);	//删除对应的productOrder
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.put("result", "orderFailed");
				}
				result.put("result", "success");
			}else{
				result.put("result", "isExsitAssyStatus");
			}
			
		}else{
			result.put("result", "empty");
		}
		return result;
	}

	//历史数据界面
	@RequestMapping(value="/dinHistoryWindows",method=RequestMethod.GET)
	public String dinHistoryWindows(Model model,HttpServletRequest request,Integer currentPage){
		//分页查询
		String moduleNo=request.getParameter("moduleNo");
		if(currentPage==null){
			currentPage=1;
		}
		List<QdsProduct> qdsProductList=new ArrayList<QdsProduct>();
		QdsProduct qdsProduct=new QdsProduct();
		try {
			int maxSize=Constants.maxSize;
			qdsProduct.setModuleNo(moduleNo);
			qdsProduct.setBeginNo((currentPage-1)*maxSize);
			qdsProduct.setPageSize(maxSize);
			int count = qProductService.getQdsProductCount(qdsProduct);	//QDS产品总数
			Page page=Constants.page(currentPage, count);	//分页
			qdsProductList=qProductService.getQdsProduct(qdsProduct);	//QDS产品清单
			model.addAttribute("qdsProductList",qdsProductList);
			model.addAttribute("currentPage", page.getCurrentPage());
			model.addAttribute("pageCount", page.getPageCount());
			model.addAttribute("count", page.getCount());
			model.addAttribute("qdsProduct",qdsProduct);	//回显
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qds/qDinHistory";
	}

	//历史详情
	@RequestMapping(value="/detailHistory.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object detailHistory(Model model,Integer id){
		Map<String,String> result=new HashMap<String,String>();
		QdsProduct qdsProduct=new QdsProduct();
		try {
			qdsProduct=qProductService.getQdsProductById(id);
			String fastjson=JSON.toJSONString(qdsProduct);
			result.put("result", fastjson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "failed");
		}
		return result;
	}
}

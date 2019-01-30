package com.foxboro.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.foxboro.entity.Role;
import com.foxboro.entity.Users;
import com.foxboro.entity.UsersRole;
import com.foxboro.service.MaterialsHistoryService;
import com.foxboro.service.RoleService;
import com.foxboro.service.UsersRoleService;
import com.foxboro.service.UsersService;
import com.foxboro.tools.Constants;
import com.foxboro.tools.MySessionContext;
import com.foxboro.tools.RandomVerifyCode;
import com.foxboro.tools.RedisAPI;

@Controller
@RequestMapping(value="/config")
public class ConfigController {
	private Logger log=Logger.getLogger(ConfigController.class);
	@Autowired
	private MaterialsHistoryService mhs;
	@Autowired
	private UsersService usersService;
	@Autowired
	private UsersRoleService usersRoleService;
	@Autowired
	private RedisAPI redisAPI;
	@Autowired
	private RoleService roleService;

	//注册时用户名检查
	@RequestMapping(value="/checkUsername.json",method=RequestMethod.POST)
	@ResponseBody
	public Object checkUsername(@RequestParam String username){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		if(username.equals("") || username==null){
			resultMap.put("username","empty");
		}else{
			if(usersService.getUsers(username)!=null){
				resultMap.put("username","exist");
			}else{
				resultMap.put("username","noexist");
			}
		} 
		return JSONArray.toJSONString(resultMap);
	}

	//注册时姓名检查
	@RequestMapping(value="/checkRealname.ajax",method=RequestMethod.POST)
	@ResponseBody
	public String checkRealname(@RequestParam String realname){
		if(realname.equals("") || realname==null){
			return "false";
		}else{
			return "true";
		} 
	}

	//注册时密码检查，（此功能已取消，由admin统一注册，系统默认生成初始密码）
	//修改密码时密码检查
	@RequestMapping(value="/checkpassword.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object checkpassword(@RequestParam String password){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		if(password.equals("") || password==null){
			resultMap.put("password","empty");
		}else if(password.length()<6){
			resultMap.put("password","false");
		}else{
			resultMap.put("password","true");
		}
		return JSONArray.toJSONString(resultMap);
	}

	//注册时重复密码检查，（此功能已取消，由admin统一注册，系统默认生成初始密码）
	//修改密码时重复密码检查
	@RequestMapping(value="/checkrepassword.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object checkrepassword(@RequestParam String repassword,@RequestParam String password){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		if(repassword.equals("") || repassword==null){
			resultMap.put("repassword","empty");
		}else if(!repassword.equals(password)){
			resultMap.put("repassword","false");
		}else{
			resultMap.put("repassword","true");
		}
		return JSONArray.toJSONString(resultMap);
	}

	//用户注册，（此功能已取消，由admin统一注册，系统默认生成初始密码）
	/*@RequestMapping("/addUser")
		public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
			PrintWriter out=response.getWriter();
			try {
				Map<String,String[]> paramterMap=request.getParameterMap();//从页面获取信息
				Users user=new Users();
				BeanUtils.populate(user, paramterMap);//使用BeanUtils将获取信息set进user中

				usersService.addUser(user);
				out.print("<script>alert('注册成功');window.location.href='index.jsp'</script>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<script>alert('注册失败');window.location.href='register'</script>");
			}
		}*/

	//用户新增
	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		try {
			//新增用户至users表中
			Users user=new Users();
			String username=request.getParameter("username");
			String realname=request.getParameter("realname");
			user.setPassword("111111");
			user.setUsername(username);
			user.setRealname(realname);
			usersService.addUser(user);	

			//新增用户角色关系
			UsersRole usersRole=new UsersRole();
			String[] category=request.getParameterValues("category");
			for (int i=0;i<category.length;i++) {
				usersRole.setUsername(username);
				usersRole.setRoleId(Integer.parseInt(category[i]));
				usersRoleService.addUserRole(usersRole);
			}

			out.print("<script>alert('注册成功');window.location.href='cAddUserWin'</script>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<script>alert('注册失败');window.location.href='cAddUserWin'</script>");
		}
	}
	/*@RequestMapping("/addUser")
		public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
			PrintWriter out=response.getWriter();
			try {
				Map<String,String[]> paramterMap=request.getParameterMap();//从页面获取信息
				Users user=new Users();
				user.setPassword("111111");
				BeanUtils.populate(user, paramterMap);//使用BeanUtils将获取信息set进user中
				log.debug("getUsername============="+user.getUsername());
				log.debug("getRealname============="+user.getRealname());
				log.debug("getCategory============="+user.getCategory());
				//int categoryId=Integer.parseInt(request.getParameter("category"));
				//user.setCategory(categoryId);
				usersService.addUser(user);
				out.print("<script>alert('注册成功');window.location.href='cAddUserWin'</script>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<script>alert('注册失败');window.location.href='cAddUserWin'</script>");
			}
		}*/

	//用户修改权限
	@RequestMapping(value="/modifyUesrFunction.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object modifyUesrFunction(String username,HttpServletRequest request,int[] category){
		Map<String,String> result=new HashMap<String,String>();
		try {
			//删除用户角色关系
			usersRoleService.delUserRole(username);
			//新增用户角色关系
			UsersRole usersRole=new UsersRole();
			for (int i=0;i<category.length;i++) {
				usersRole.setUsername(username);
				usersRole.setRoleId((category[i]));
				usersRoleService.addUserRole(usersRole);
			}
			//删除redis中对应的内容
			String keys1="moduleList"+username;
			String keys2="packingMainList"+username;
			String keys3="configMainList"+username;
			redisAPI.del(keys1);
			redisAPI.del(keys2);
			redisAPI.del(keys3);
			result.put("modifyResult", "success");
		} catch (Exception e) {
			result.put("modifyResult", "failed");
		}
		return result;
	}

	//检查验证码
	@RequestMapping(value="/verifyCode.json",method=RequestMethod.POST)
	@ResponseBody
	public Object verifyCode(HttpServletRequest request,@RequestParam String code){
		Map<String,String> hm=new HashMap<String,String>();
		String randomStr=(String) request.getSession().getAttribute("RANDOMSTR");
		if(code!=null && !code.equals("")){
			if(code.toUpperCase().equals(randomStr)){
				hm.put("codeResult", "true");
			}else{
				hm.put("codeResult", "false");
			}
		}else{
			hm.put("codeResult", "empty");
		}
		return hm;
	}

	//生成验证码
	@RequestMapping(value="/getVerify")
	public void getVerify(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomVerifyCode rvc = new RandomVerifyCode();
		rvc.VerifyCode(response,request);
	}

	//修改密码界面
	@RequestMapping(value="/changePwdWin",method=RequestMethod.GET)
	public String changePasswordWin(){
		return "changePwd";
	}

	//修改密码时旧密码检查
	@RequestMapping(value="/checkOldPwd.ajax",method=RequestMethod.POST)
	@ResponseBody
	public Object checkOldPwd(@RequestParam String oldPwd,HttpServletRequest request){
		HashMap<String,String> resultMap=new HashMap<String,String>();
		Users user=(Users) request.getSession().getAttribute("user");
		if(oldPwd.equals("") || oldPwd==null){
			resultMap.put("oldPwd","empty");
		}else if(oldPwd.equals(user.getPassword())){
			resultMap.put("oldPwd","true");
		}else{
			resultMap.put("oldPwd","false");
		}
		return JSONArray.toJSONString(resultMap);
	}

	//修改密码
	@RequestMapping(value="/changePwd",method=RequestMethod.POST)
	public void changePwd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		try {
			String newPwd=request.getParameter("repassword");
			Users user=(Users) request.getSession().getAttribute("user");
			user.getId();
			user.setPassword(newPwd);
			usersService.updateUser(user);
			//注销当前用户
			MySessionContext.delSession(request.getSession());
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
			//跳转到重新登录界面
			out.print("<script>alert('更改成功');window.location.href='../index.jsp'</script>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<script>alert('更改失败');window.location.href='../index.jsp'</script>");
		}
	}

	// 按id删除用户
	@RequestMapping(value="/delUser.json",method=RequestMethod.POST)
	@ResponseBody
	public Object delUser(int id,String username){
		Map<String,String> hm=new HashMap<String,String>();
		if(id!=0){
			try {
				if(mhs.getMaterialsHistoryByModify(id)!=0){
					hm.put("delresult", "exsit");
				}else{
					usersRoleService.delUserRole(username);	//删除用户对应的角色
					usersService.delUserById(id);	//删除用户
					hm.put("delresult", "true");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hm.put("delresult", "false");
			}
		}else{
			hm.put("delresult", "notexsit");
		}
		return hm;
	}

	//用户管理界面
		@RequestMapping(value="/usersManageWindows",method=RequestMethod.GET)
		public String usersManageWindows(Model model,Integer currentPage){
			List<Users> usersList=new ArrayList<Users>();
			//分页查询
			if(currentPage==null){
				currentPage=1;
			}
			int count=usersService.getAllUsersCount();
			int maxSize=Constants.maxSize;
			int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
			if(pageCount==0)
				pageCount=1;
			if (currentPage <1)
				currentPage=1;
			if (currentPage > pageCount)
				currentPage = pageCount;

			usersList=usersService.getAllUsers((currentPage-1)*maxSize, maxSize);
			for (Users users : usersList) {
				List<Role> role=roleService.getRoleByUsername(users.getUsername());
				users.setRole(role);
			}
			model.addAttribute("usersList",usersList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("count", count);
			return "config/cUser";
		}
	
	//新增用户界面
	@RequestMapping(value="/cAddUserWin",method=RequestMethod.GET)
	public String cAddUser(){
		return "config/cAddUser";
	}
}

package com.foxboro.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.foxboro.entity.Authority;
import com.foxboro.entity.Function;
import com.foxboro.entity.Menu;
import com.foxboro.entity.Role;
import com.foxboro.entity.Users;
import com.foxboro.entity.UsersRole;
import com.foxboro.service.FunctionService;
import com.foxboro.service.MaterialsHistoryService;
import com.foxboro.service.RoleService;
import com.foxboro.service.UsersRoleService;
import com.foxboro.service.UsersService;
import com.foxboro.tools.Constants;
import com.foxboro.tools.MySessionContext;
import com.foxboro.tools.RandomVerifyCode;
import com.foxboro.tools.RedisAPI;

@Controller
public class UsersController {
	private Logger log=Logger.getLogger(UsersController.class);
	@Autowired
	private UsersService usersService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private UsersRoleService usersRoleService;
	@Autowired
	private RedisAPI redisAPI;

	//跳转至登录页面
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("error", request.getAttribute("error"));
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//用户登录
	@RequestMapping("/dologin")
	public ModelAndView dologin(HttpServletRequest request) throws IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		HashMap<String,HttpSession> hm=MySessionContext.getMySessionMap();
		Map<String,Object> model=new HashMap<String,Object>();
		List<Menu> moduleList=null;
		Users user=null;
		String ip=request.getRemoteAddr();//获取当前ip

		try {
			user=usersService.getUsers(username);
			if(user.getPassword().equals(password)){
				//屏蔽单一登录功能
				/*for (HttpSession value : hm.values()) {
					Users u=(Users) value.getAttribute("user");
					if(u==null){
						request.getSession().setAttribute("user", user);
						request.getSession().setAttribute("localIp", ip);
						MySessionContext.addSession(request.getSession());
						return "redirect:welcomeWindow";
					}else{
						if(u.getUsername().equals(username)){//此账号已登录
							model.addAttribute("error","此账号已在其它地方登录    <a href='kickout.do?username="+user.getUsername()+"' onclick='return confirm('是否要踢出？')'>踢出<a>");
							return "forward:login";
						}else{//此账号未登录
							request.getSession().setAttribute("user", user);
							request.getSession().setAttribute("localIp", ip);
							MySessionContext.addSession(request.getSession());
							return "redirect:welcomeWindow";
						}
					}	
				}*/
				request.getSession().setAttribute("user", user);	//user放入session
				Date loginTime=user.getLastLoginTime();
				request.getSession().setAttribute("loginTime", loginTime);	//登录时间放入session
				request.getSession().setAttribute("localIp", ip);	//IP放入session
				List<Role> role=roleService.getRoleByUsername(username);	//获取角色信息
				request.getSession().setAttribute("role", role);	//用户角色放入session
				MySessionContext.addSession(request.getSession());
				/**
				 * key:moduleList+username eg:"moduleListxy"
				 * value:moduleList
				 */
				//redis里有没有数据
				if(!redisAPI.exist("moduleList"+user.getUsername())){
					moduleList=getModulMenuByCurrentUser(role);	//调用方法：根据当前用户角色获取模块菜单
					String fastjson=null;
					if(moduleList!=null){	//JSON
						fastjson=JSON.toJSONString(moduleList);
						model.put("moduleList",fastjson);
						//request.getSession().setAttribute(Constants.SESSION_MODULE_MENU, model);
						request.getSession().setAttribute("moduleList", model);
						redisAPI.set("moduleList"+user.getUsername(), fastjson);
					}
				}else{	//redis里有数据，直接从redis里获取
					String redisModuleKeyString=redisAPI.get("moduleList"+user.getUsername());
					if(redisModuleKeyString!=null && !redisModuleKeyString.equals("")){
						request.getSession().setAttribute("moduleList", redisModuleKeyString);
					}else{
						return new ModelAndView("redirect:login");
					}
				}
				user.setLastLoginTime(new Date());	//登录成功后修改登录时间
				usersService.updateUser(user);
				//return new ModelAndView("welcome",model);
				return new ModelAndView("welcome");
			}else{
				request.setAttribute("error","密码不正确");
				return new ModelAndView("forward:login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error","用户名不正确");
			return new ModelAndView("forward:login");
		}
	}

	/**
	 * 根据当前用户角色id获取模块菜单
	 * @param roleId
	 * @return
	 */
	protected List<Menu> getModulMenuByCurrentUser(List<Role> roleIdList){
		List<Menu> menuList=new ArrayList<Menu>();
		Authority authority=new Authority();
		for (Role roleId : roleIdList) {	//遍历roleIdList，对应有多个角色的用户
			authority.setRoleId(roleId.getid());	//设置roleId
			try {
				List<Function> moduleList=functionService.getModuleFunctionList(authority);	//获取模块菜单
				if(moduleList!=null){
					for (Function function : moduleList) {
						Menu menu=new Menu();
						menu.setModuleMenu(function);
						menuList.add(menu);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return menuList;
	}

	/**
	 * 根据当前用户角色id获取相应的主菜单、子菜单
	 * @param roleId 角色id
	 * @param parentId 父类id 1:包材系统 2:QDS系统 3:系统设置
	 * @return
	 */
	protected List<Menu> getMenuByCurrentUser(int roleId,int parentId){
		List<Menu> menuList=new ArrayList<Menu>();
		Authority authority=new Authority();
		authority.setRoleId(roleId);	//设置roleId
		authority.setParentId(parentId); //设置parentId
		try {
			List<Function> packingMainList=functionService.getMenuFunctionList(authority); //获取包材主菜单
			if(packingMainList!=null){
				for (Function function : packingMainList) {
					Menu menu=new Menu();
					menu.setMainMenu(function);	//存入主菜单
					function.setRoleId(roleId);
					List<Function> subList=functionService.getSubFunctionList(function);	//获取子菜单
					if(subList!=null){
						menu.setSubMenu(subList);	//存入子菜单
					}
					menuList.add(menu);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuList;
	}

	//欢迎主页面
	@RequestMapping("/welcomeWindow")
	public String welcomeWindow(HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute("user")==null){
			return "redirect:login";
		}else{
			return "welcome";
		}
	}

	//包材管理系统主页面
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute("user")==null){
			return new ModelAndView("redirect:login");
		}else{
			Map<String,Object> model=new HashMap<String,Object>();
			Users user=(Users) request.getSession().getAttribute("user");
			List<Menu> mList=null;
			int parentId=1;	//设置查询范围为包材系统模块下的主菜单
			/**
			 * key:packingMainList+username eg:"packingMainListxy"
			 * value:mainList
			 */
			//redis里有没有数据
			if(!redisAPI.exist("packingMainList"+user.getUsername())){
				//查询username对应的roleId集合，一个用户会有多个角色
				ArrayList<Integer> role=usersRoleService.getRoleIdByUsername(user.getUsername());
				for (Integer integer : role) {
					switch(integer){
					case 1:
						mList=getMenuByCurrentUser(1,parentId);break;	//Admin获取包材系统的主菜单
					case 2:
						mList=getMenuByCurrentUser(2,parentId);break;	//包材管理员获取包材系统的主菜单
					case 3:
						mList=getMenuByCurrentUser(3,parentId);break;	//包材普通用户获取包材系统的主菜单
					}
				}
				String fastjson=null;
				if(mList!=null){	//JSON
					fastjson=JSON.toJSONString(mList);
					model.put("packingMainList",fastjson);
					//request.getSession().setAttribute(Constants.SESSION_PACKING_MENU, model);
					request.getSession().setAttribute("packingMainList", model);
					redisAPI.set("packingMainList"+user.getUsername(), fastjson);
				}
			}else{	//redis里有数据，直接从redis里获取
				String redisPackingMainKeyString=redisAPI.get("packingMainList"+user.getUsername());
				if(redisPackingMainKeyString!=null && !redisPackingMainKeyString.equals("")){
					request.getSession().setAttribute("packingMainList", redisPackingMainKeyString);
				}else{
					return new ModelAndView("redirect:login");
				}
			}
			return new ModelAndView("packing/main");
		}
	}

	//注销
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){
		//MySessionContext msc= MySessionContext.getInstance();
		//删除对应的loginAccount
		Users user=(Users) request.getSession().getAttribute("user");
		MySessionContext.delSession(request.getSession());
		/*for(int i=0;i<Constants.loginAccount.size();i++){
			if(user.equals(Constants.loginAccount.get(i))){
				Constants.loginAccount.remove(i);
			}
		}*/
		request.getSession().removeAttribute("role");
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//跳转到注册页面
	@RequestMapping(value="/register")
	public String register(){
		return "register";
	}

	//踢出
	@RequestMapping(value="/kickout",method=RequestMethod.GET)
	public String kickout(@RequestParam String username,HttpServletResponse response,
			HttpServletRequest request,Model model) throws IOException{
		//PrintWriter out=response.getWriter();
		//out.print("<script>confirm='是否要踢出？'</script>");

		HashMap<String,HttpSession> sessionMap=MySessionContext.getMySessionMap();
		for (HttpSession value : sessionMap.values()) {
			Users user=(Users) value.getAttribute("user");
			if(user.getUsername().equals(username)){//在自定义msc中找到对应用户信息，然后进行销毁session
				MySessionContext.delSession(request.getSession());
				request.getSession().removeAttribute("user");
				//request.getSession().invalidate();
				break;
			}
		}
		/*for(int i=0;i<Constants.loginAccount.size();i++){
			if(Constants.loginAccount.get(i).getUsername().equals(username)){
				Constants.loginAccount.remove(i);//从用户列表中踢除用户
				Users user=(Users) request.getSession().getAttribute("user");
				request.getSession().removeAttribute("user");
				request.getSession().invalidate();
			}
		}*/
		request.setAttribute("error","对方已被踢出,请重新登录");
		return "forward:login";
	}

	

	//系统设置模块界面
	@RequestMapping(value="/cMain",method=RequestMethod.GET)
	public ModelAndView cMain(HttpSession session){
		Map<String,Object> model=new HashMap<String,Object>();
		Users user=(Users) session.getAttribute("user");
		List<Menu> mList=null;
		int parentId=3; //设置查询范围为系统设置模块下的主菜单
		/**
		 * key:configMainList+username eg:"configMainListxy"
		 * value:mainList
		 */
		//redis里有没有数据
		if(!redisAPI.exist("configMainList"+user.getUsername())){
			//查询username对应的roleId集合，一个用户会有多个角色
			ArrayList<Integer> role=usersRoleService.getRoleIdByUsername(user.getUsername());
			for (Integer integer : role) {
				switch(integer){
				case 1:
					mList=getMenuByCurrentUser(1,parentId);break;	//Admin获取包材系统的主菜单
				}
			}
			String fastjson=null;
			if(mList!=null){	//JSON
				fastjson=JSON.toJSONString(mList);
				model.put("configMainList",fastjson);
				//request.getSession().setAttribute(Constants.SESSION_PACKING_MENU, model);
				session.setAttribute("configMainList", model);
				redisAPI.set("configMainList"+user.getUsername(), fastjson);
			}
		}else{	//redis里有数据，直接从redis里获取
			String redisconfigMainKeyString=redisAPI.get("configMainList"+user.getUsername());
			if(redisconfigMainKeyString!=null && !redisconfigMainKeyString.equals("")){
				session.setAttribute("configMainList", redisconfigMainKeyString);
			}else{
				return new ModelAndView("redirect:login");
			}
		}
		return new ModelAndView("config/cMain");
	}

	//qds模块界面
	@RequestMapping(value="/qMain",method=RequestMethod.GET)
	public ModelAndView qMain(HttpSession session){
		Map<String,Object> model=new HashMap<String,Object>();
		Users user=(Users) session.getAttribute("user");
		List<Menu> mList=null;
		int parentId=2; //设置查询范围为qds模块下的主菜单
		/**
		 * key:qdsMainList+username eg:"qdsMainListxy"
		 * value:mainList
		 */
		//redis里有没有数据
		if(!redisAPI.exist("qdsMainList"+user.getUsername())){
			//查询username对应的roleId集合，一个用户会有多个角色
			ArrayList<Integer> role=usersRoleService.getRoleIdByUsername(user.getUsername());
			for (Integer integer : role) {
				switch(integer){
				case 1:
					mList=getMenuByCurrentUser(1,parentId);break;	//Admin获取qds系统的主菜单
				case 4:
					mList=getMenuByCurrentUser(4,parentId);break;	//qds管理员获取qds系统的主菜单
				case 5:
					mList=getMenuByCurrentUser(5,parentId);break;	//qds检验员获取qds系统的主菜单
				case 6:
					mList=getMenuByCurrentUser(6,parentId);break;	//qds普通用户获取qds系统的主菜单
				}
			}
			String fastjson=null;
			if(mList!=null){	//JSON
				fastjson=JSON.toJSONString(mList);
				model.put("qdsMainList",fastjson);
				//request.getSession().setAttribute(Constants.SESSION_PACKING_MENU, model);
				session.setAttribute("qdsMainList", model);
				redisAPI.set("qdsMainList"+user.getUsername(), fastjson);
			}
		}else{	//redis里有数据，直接从redis里获取
			String redisconfigMainKeyString=redisAPI.get("qdsMainList"+user.getUsername());
			if(redisconfigMainKeyString!=null && !redisconfigMainKeyString.equals("")){
				session.setAttribute("qdsMainList", redisconfigMainKeyString);
			}else{
				return new ModelAndView("redirect:login");
			}
		}
		return new ModelAndView("qds/qMain");
	}
}

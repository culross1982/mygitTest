package com.foxboro.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.foxboro.entity.Users;

public class SysInterceptor extends HandlerInterceptorAdapter{
	private Logger log=Logger.getLogger(SysInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		HttpSession session=request.getSession();
		Users user=(Users) session.getAttribute("user");
		if(user!=null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/403.jsp");
			return false;
		}
	}
}

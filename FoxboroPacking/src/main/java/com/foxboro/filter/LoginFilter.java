package com.foxboro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.foxboro.entity.Users;
import com.foxboro.listener.SessionListener;

public class LoginFilter implements Filter{
	private Logger log=Logger.getLogger(SessionListener.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		String localIp=request.getRemoteAddr();//获取本地ip
		log.info("localIp========"+localIp);
		String sessionId=request.getSession().getId();//获取sessionId
		String username=request.getParameter("username");
		if(username!=null && username.equals("")){
			
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

package com.foxboro.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.foxboro.entity.Users;
import com.foxboro.tools.MySessionContext;

public class SessionListener implements HttpSessionListener {
	private Logger log=Logger.getLogger(SessionListener.class);
	
	//访问浏览器时通过监听器把sessionId和session存入HashMap中
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		// TODO Auto-generated method stub
		HttpSession session = httpSessionEvent.getSession();
		log.info("sessionId上线了=========="+session.getId());
		MySessionContext.addSession(session);  //sessionId和session存入HashMap中
        
	}

	//session销毁时自动清除HashMap中当前Session对应的信息
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		// TODO Auto-generated method stub
		HttpSession session = httpSessionEvent.getSession();
		log.info("sessionId到时销毁了=========="+session.getId());
		MySessionContext.delSession(session);//使session失效,清除当前Session对应的登录用户
	}

}

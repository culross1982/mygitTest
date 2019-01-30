package com.foxboro.tools;

import java.util.HashMap;

import javax.servlet.http.HttpSession;


public class MySessionContext {
	//private static MySessionContext instance;
	private static HashMap<String,HttpSession> sessionMap=new HashMap<String,HttpSession>();//Map静态成员存放sessionId和session

	/*private MySessionContext(){
		sessionMap=new HashMap<String,HttpSession>();
	}

	public static MySessionContext getInstance(){
		if(instance==null){
			instance=new MySessionContext();
		}
		return instance;
	}*/

	public static synchronized void addSession(HttpSession session) {  
		if (session != null) {  
			sessionMap.put(session.getId(), session);  
		}  
	}  

	public static synchronized void delSession(HttpSession session) {  
		if (session != null) {  
			HttpSession session2=sessionMap.remove(session.getId()); 
			if(session2!=null){
				session2.invalidate();//使session失效,清除当前Session对应的登录用户
			}
		}  
	} 

	public static synchronized HttpSession getSession(String sessionID) {  
		if (sessionID == null) {  
			return null;  
		}  
		return sessionMap.get(sessionID);  
	}  
	
	public static HashMap<String,HttpSession> getMySessionMap(){
		return sessionMap;
	}
}

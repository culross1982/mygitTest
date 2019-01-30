package com.foxboro.tools;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author SESA320249
 *
 */
public class OldMySessionContext {
	private static OldMySessionContext instance;
	private HashMap<String,HttpSession> sessionMap;

	private OldMySessionContext(){
		sessionMap=new HashMap<String,HttpSession>();
	}

	public static OldMySessionContext getInstance(){
		if(instance==null){
			instance=new OldMySessionContext();
		}
		return instance;
	}

	public synchronized void addSession(HttpSession session) {  
		if (session != null) {  
			sessionMap.put(session.getId(), session);  
		}  
	}  

	public synchronized void delSession(HttpSession session) {  
		if (session != null) {  
			sessionMap.remove(session.getId());  
		}  
	} 

	public synchronized HttpSession getSession(String sessionID) {  
		if (sessionID == null) {  
			return null;  
		}  
		return sessionMap.get(sessionID);  
	}  
}

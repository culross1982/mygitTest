package com.foxboro.tools;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeOut {
	public TimeOut(HttpServletResponse response,HttpServletRequest request){
		try {
			response.sendRedirect(request.getContextPath()+"/502.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

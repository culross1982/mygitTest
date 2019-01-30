package com.foxboro.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//访问时间工具类
public class VisitTime {
	/**
	 * 显示来访者上次访问网站的时间
	 * @param request
	 * @param response
	 * @param username 登录用户的用户名，用来给cookie命名，解决1台设备登录不同账号时各自保存访问时间
	 * @return
	 */
	public Object VisitTime(HttpServletRequest request,HttpServletResponse response,String username){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time=sdf.format(date);
		//发送当前时间到客户端cookie
		Cookie cookie=new Cookie(username, time);//把访问时间保存到cookie中的用户名中
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);//保存到本地
		//获取客户端浏览器发送过来的cookie数据
		String timeValue=null;
		Cookie[] cookies=request.getCookies();//获取cookie数组
		for (Cookie cookie1 : cookies) {
			if(cookie1.getName().equals(username)){//获取和登录用户名一致的cookie时间
				timeValue=cookie1.getValue();
			}
		}
		Map<String,String> hm=new HashMap<String,String>();
		if(timeValue==null){//用户第一次登录时不会有cookie信息，需进行null判断
			hm.put("visitResult", "欢迎使用此系统");
		}else{
			hm.put("visitResult", "您上次登录的时间是："+timeValue);
		}
		return hm;
	}
}

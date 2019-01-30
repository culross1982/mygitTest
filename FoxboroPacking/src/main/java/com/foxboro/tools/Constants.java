package com.foxboro.tools;

import java.util.ArrayList;
import java.util.List;

import com.foxboro.entity.MaterialsOrder;
import com.foxboro.entity.Page;
import com.foxboro.entity.Users;

public class Constants {
	public final static int maxSize=50;	//每页显示的数量
	public static ArrayList<Users> loginAccount;	//“单一登录”的用户储存集合
	public static final String SESSION_USER="userSession";
	public static final String SESSION_MODULE_MENU="moduleMenu";	//当前登录用户的模块菜单
	public static final String SESSION_PACKING_MENU="packingMenu";	//当前登录用户的包材主菜单、子菜单
	public static final List<MaterialsOrder> matList=new ArrayList<MaterialsOrder>();	//存放packing的临时订购数据
	public static boolean MATERIALS_ORDER_FLAG=true;	//包材订购页面标志位，同一时刻只能1个用户使用此功能。
	
	/**
	 * 分页
	 * @param currentPage 当前页码
	 * @param count 总数量
	 * @return
	 */
	public static Page page(Integer currentPage,int count){
		
		int maxSize=Constants.maxSize;
		int pageCount=count%maxSize==0?count/maxSize:(count/maxSize+1);
		if(pageCount==0)
			pageCount=1;
		if (currentPage <1)
			currentPage=1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		Page page=new Page();
		page.setCount(count);
		page.setCurrentPage(currentPage);
		page.setPageCount(pageCount);
		return page;
	}
}

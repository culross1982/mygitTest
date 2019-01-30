package com.foxboro.service;

import java.util.List;

import com.foxboro.entity.Authority;
import com.foxboro.entity.Function;
import com.foxboro.entity.UsersRole;

public interface FunctionService {
	/**
	 * 获取模块菜单
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public List<Function> getModuleFunctionList(Authority authority) throws Exception;
	
	/**
	 * 获取各功能主菜单
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	public List<Function> getMenuFunctionList(Authority authority) throws Exception;
	
	/**
	 * 获取子菜单
	 * @param function
	 * @return
	 * @throws Exception
	 */
	public List<Function> getSubFunctionList(Function function) throws Exception;
}

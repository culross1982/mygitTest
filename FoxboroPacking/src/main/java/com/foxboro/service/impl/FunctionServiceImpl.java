package com.foxboro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.FunctionDao;
import com.foxboro.entity.Authority;
import com.foxboro.entity.Function;
import com.foxboro.entity.UsersRole;
import com.foxboro.service.FunctionService;
@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionDao functionDao;

	//获取模块菜单
	@Override
	public List<Function> getModuleFunctionList(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return functionDao.getModuleFunctionList(authority);
	}

	//获取各功能主菜单
	@Override
	public List<Function> getMenuFunctionList(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return functionDao.getMenuFunctionList(authority);
	}

	//获取分菜单
	@Override
	public List<Function> getSubFunctionList(Function function) throws Exception {
		// TODO Auto-generated method stub
		return functionDao.getSubFunctionList(function);
	}





}

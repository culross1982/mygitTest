package com.foxboro.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.UsersRoleDao;
import com.foxboro.entity.UsersRole;
import com.foxboro.service.UsersRoleService;
@Service
public class UsersRoleServiceImpl implements UsersRoleService {
	@Autowired
	private UsersRoleDao usersRoleDao;
	
	//新增用户角色关系
	@Override
	public void addUserRole(UsersRole usersRole) throws Exception{
		// TODO Auto-generated method stub
		usersRoleDao.addUserRole(usersRole);
	}

	//根据用户名查找对应角色
	@Override
	public ArrayList<Integer> getRoleIdByUsername(String username) {
		// TODO Auto-generated method stub
		return usersRoleDao.getRoleIdByUsername(username);
	}

	//根据用户名删除对应角色
	@Override
	public void delUserRole(String username) throws Exception{
		// TODO Auto-generated method stub
		usersRoleDao.delUserRole(username);
	}
	
	

}

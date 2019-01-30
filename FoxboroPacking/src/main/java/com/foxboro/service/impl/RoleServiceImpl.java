package com.foxboro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.RoleDao;
import com.foxboro.entity.Role;
import com.foxboro.entity.Users;
import com.foxboro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	//根据用户名获取角色信息
	@Override
	public List<Role> getRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return roleDao.getRoleByUsername(username);
	}

	//根据用户名获取修改状态启停 0：启用（默认）1：停止
	@Override
	public Integer getModifyStatueByUsername(Users user) {
		// TODO Auto-generated method stub
		return roleDao.getModifyStatueByUsername(user);
	}

}

package com.foxboro.service;

import java.util.ArrayList;

import com.foxboro.entity.UsersRole;

public interface UsersRoleService {
	/**
	 * 新增用户角色关系
	 * @param usersRole
	 */
	public void addUserRole(UsersRole usersRole) throws Exception;
	
	/**
	 * 根据用户名查找对应角色
	 * @param username
	 * @return roleId
	 */
	public ArrayList<Integer> getRoleIdByUsername(String username);
	
	/**
	 * 根据用户名删除对应角色
	 * @param username
	 */
	public void delUserRole(String username)throws Exception;
}

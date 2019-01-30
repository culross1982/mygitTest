package com.foxboro.dao;

import java.util.List;

import com.foxboro.entity.Role;
import com.foxboro.entity.Users;

public interface RoleDao {
	/**
	 * 根据用户名获取角色信息
	 * @param username
	 * @return
	 */
	public List<Role> getRoleByUsername(String username);
	
	/**
	 * 根据用户名获取修改状态启停 0：启用（默认）1：停止
	 * @param user
	 * @return
	 */
	public Integer getModifyStatueByUsername(Users user);
}

package com.foxboro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.Users;

public interface UsersDao {
	/**
	 * 查找用户
	 * @param username
	 * @return
	 */
	public Users getUsers(String username);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public void addUser(Users user);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<Users> getAllUsers(@Param("beginNo")int beginNo,
								   @Param("pageSize")int pageSize);
	
	/**
	 * 获取所有用户数量
	 * @return
	 */
	public int getAllUsersCount();
	
	/**
	 * 按id删除用户
	 * @param id
	 * @return
	 */
	public void delUserById(int id);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public void updateUser(Users user) throws Exception;
	
}

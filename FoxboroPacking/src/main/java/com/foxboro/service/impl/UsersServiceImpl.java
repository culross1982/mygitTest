package com.foxboro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.UsersDao;
import com.foxboro.entity.Users;
import com.foxboro.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersDao;
		
	//查找用户
	@Override
	public Users getUsers(String username){
		// TODO Auto-generated method stub
		return usersDao.getUsers(username);
	}
	
	//添加用户
	@Override
	public void addUser(Users user) {
		// TODO Auto-generated method stub
		usersDao.addUser(user);
	}

	//获取所有用户
	@Override
	public List<Users> getAllUsers(int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return usersDao.getAllUsers(beginNo, pageSize);
	}

	////获取所有用户
	@Override
	public int getAllUsersCount() {
		// TODO Auto-generated method stub
		return usersDao.getAllUsersCount();
	}

	//按id删除用户
	@Override
	public void delUserById(int id) {
			usersDao.delUserById(id);
	}

	//更新用户
	@Override
	public void updateUser(Users user) throws Exception {
		// TODO Auto-generated method stub
		usersDao.updateUser(user);
	}
}

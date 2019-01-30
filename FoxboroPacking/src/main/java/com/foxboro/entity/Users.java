package com.foxboro.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Users {
	private int id;
	private String username;
	private String password;
	private String realname;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date lastLoginTime;
	private String changeRole;	//用户角色更改标志位 0：未更改 1：更改
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date createTime;
	
	private List<Materials> materials;
	private List<Role> role;	//存放用户所拥有的角色信息
	private String roleName; //方便（根据用户名获取修改状态启停 0：启用（默认）1：停止）使用
	
	public String getChangeRole() {
		return changeRole;
	}
	public void setChangeRole(String changeRole) {
		this.changeRole = changeRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public List<Materials> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Materials> materials) {
		this.materials = materials;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

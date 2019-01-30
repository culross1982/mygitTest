package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsModule extends Base{
	private String module;
	private String ver;
	private String part;
	private String category;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date createTime;
	private int createBy;
	private int qdsProCategoryId;
	
	private String realname;	//用于查询
	private String qdsProCategoryName;	//用于查询
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getQdsProCategoryName() {
		return qdsProCategoryName;
	}
	public void setQdsProCategoryName(String qdsProCategoryName) {
		this.qdsProCategoryName = qdsProCategoryName;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public int getQdsProCategoryId() {
		return qdsProCategoryId;
	}
	public void setQdsProCategoryId(int qdsProCategoryId) {
		this.qdsProCategoryId = qdsProCategoryId;
	}
	
	
}

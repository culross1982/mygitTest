package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProductAssy extends Base{
	private String moduleNo;
	private String assyNo;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date assyTime;
	private int assyBy;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date assyModifyTime;
	private int assyModifyBy;
	private int qdsProCategoryId;
	
	private String realname;	//用于查询
	private String modifyName;	//用于查询
	private String qdsProCategoryName;	//用于查询
	
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public Date getAssyModifyTime() {
		return assyModifyTime;
	}
	public void setAssyModifyTime(Date assyModifyTime) {
		this.assyModifyTime = assyModifyTime;
	}
	public int getAssyModifyBy() {
		return assyModifyBy;
	}
	public void setAssyModifyBy(int assyModifyBy) {
		this.assyModifyBy = assyModifyBy;
	}
	public String getQdsProCategoryName() {
		return qdsProCategoryName;
	}
	public void setQdsProCategoryName(String qdsProCategoryName) {
		this.qdsProCategoryName = qdsProCategoryName;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public String getAssyNo() {
		return assyNo;
	}
	public void setAssyNo(String assyNo) {
		this.assyNo = assyNo;
	}
	public Date getAssyTime() {
		return assyTime;
	}
	public void setAssyTime(Date assyTime) {
		this.assyTime = assyTime;
	}
	public int getAssyBy() {
		return assyBy;
	}
	public void setAssyBy(int assyBy) {
		this.assyBy = assyBy;
	}
	public int getQdsProCategoryId() {
		return qdsProCategoryId;
	}
	public void setQdsProCategoryId(int qdsProCategoryId) {
		this.qdsProCategoryId = qdsProCategoryId;
	}

	
	
}

package com.foxboro.entity;

import java.util.Date;

public class QdsProductOrder extends Base{
	private String order;
	private String module;
	private String ver;
	private int pwdQuantity;
	private String snStart;
	private String snEnd;
	private int createBy;
	private Date createTime;
	private int qdsProCategoryId;
	
	private String qdsProCategoryName;
	private String realname;	//用于查询 
	
	public String getSnStart() {
		return snStart;
	}
	public void setSnStart(String snStart) {
		this.snStart = snStart;
	}
	public String getSnEnd() {
		return snEnd;
	}
	public void setSnEnd(String snEnd) {
		this.snEnd = snEnd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getQdsProCategoryId() {
		return qdsProCategoryId;
	}
	public void setQdsProCategoryId(int qdsProCategoryId) {
		this.qdsProCategoryId = qdsProCategoryId;
	}
	public String getQdsProCategoryName() {
		return qdsProCategoryName;
	}
	public void setQdsProCategoryName(String qdsProCategoryName) {
		this.qdsProCategoryName = qdsProCategoryName;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getPwdQuantity() {
		return pwdQuantity;
	}
	public void setPwdQuantity(int pwdQuantity) {
		this.pwdQuantity = pwdQuantity;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

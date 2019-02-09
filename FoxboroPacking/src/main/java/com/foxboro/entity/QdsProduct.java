package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProduct extends Base{
	private String moduleNo;
	private int assyStatus;
	private String testStatus;
	private int inspectionBy;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date inspectionTime;
	private int inspectionStatus;
	private int qdsProductOrderId;
	private int qdsProCategoryId;
	
	private String qdsProCategoryName;
	private String order;
	private String realName;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public int getAssyStatus() {
		return assyStatus;
	}
	public void setAssyStatus(int assyStatus) {
		this.assyStatus = assyStatus;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public int getInspectionBy() {
		return inspectionBy;
	}
	public void setInspectionBy(int inspectionBy) {
		this.inspectionBy = inspectionBy;
	}
	public Date getInspectionTime() {
		return inspectionTime;
	}
	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	public int getInspectionStatus() {
		return inspectionStatus;
	}
	public void setInspectionStatus(int inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
	public int getQdsProductOrderId() {
		return qdsProductOrderId;
	}
	public void setQdsProductOrderId(int qdsProductOrderId) {
		this.qdsProductOrderId = qdsProductOrderId;
	}
	
}

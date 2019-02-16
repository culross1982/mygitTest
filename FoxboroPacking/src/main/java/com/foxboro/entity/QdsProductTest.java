package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProductTest extends Base{
	private String moduleNo;
	private String testCode;
	private String testStatus;
	private String status;
	private String testResult;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date testTime;
	private int testBy;
	private String testEquipment;
	private String testDiscription;
	private Integer errorStatus;
	
	private String realname;
	private int qdsProCategoryId;	//用于查询
	private String errorCodeId;
	private String remark;

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getErrorCodeId() {
		return errorCodeId;
	}
	public void setErrorCodeId(String errorCodeId) {
		this.errorCodeId = errorCodeId;
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
	public String getTestEquipment() {
		return testEquipment;
	}
	public void setTestEquipment(String testEquipment) {
		this.testEquipment = testEquipment;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}
	public int getTestBy() {
		return testBy;
	}
	public void setTestBy(int testBy) {
		this.testBy = testBy;
	}
	public String getTestDiscription() {
		return testDiscription;
	}
	public void setTestDiscription(String testDiscription) {
		this.testDiscription = testDiscription;
	}
	public Integer getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(Integer errorStatus) {
		this.errorStatus = errorStatus;
	}
	public int getQdsProCategoryId() {
		return qdsProCategoryId;
	}
	public void setQdsProCategoryId(int qdsProCategoryId) {
		this.qdsProCategoryId = qdsProCategoryId;
	}
		
}

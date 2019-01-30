package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProductTest extends Base{
	private String testNo;
	private String testCode;
	private String testStatus;
	private String status;
	private String testResult;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date testTime;
	private int testBy;
	private String testEquipment;
	private String discription;
	private int qdsProCategoryId;
	
	private String realname;
	private String qdsProCategoryName;	//用于查询
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTestNo() {
		return testNo;
	}
	public void setTestNo(String testNo) {
		this.testNo = testNo;
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
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
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
	
	
}

package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProduct extends Base{
	private String moduleNo;
	private int assyStatus;
	private String testCode;
	private String testStatus;
	private String status;
	private String testResult;
	private int testBy;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date testTime;
	private String testEquipment;
	private String testDiscription;
	private int errorStatus;
	private int inspectionBy;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date inspectionTime;
	private int inspectionStatus;
	private int qdsProductOrderId;
	private int qdsProCategoryId;
	
	private String qdsProCategoryName;
	private String order;
	
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
	public int getTestBy() {
		return testBy;
	}
	public void setTestBy(int testBy) {
		this.testBy = testBy;
	}
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}
	public String getTestEquipment() {
		return testEquipment;
	}
	public void setTestEquipment(String testEquipment) {
		this.testEquipment = testEquipment;
	}
	public String getTestDiscription() {
		return testDiscription;
	}
	public void setTestDiscription(String testDiscription) {
		this.testDiscription = testDiscription;
	}
	public int getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(int errorStatus) {
		this.errorStatus = errorStatus;
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

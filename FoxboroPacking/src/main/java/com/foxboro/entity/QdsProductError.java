package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProductError extends Base{
	private String moduleNo;
	private int errorCodeId;
	private int repairBy;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date repairTime;
	private String remark;
	
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public int getErrorCodeId() {
		return errorCodeId;
	}
	public void setErrorCodeId(int errorCodeId) {
		this.errorCodeId = errorCodeId;
	}
	public int getRepairBy() {
		return repairBy;
	}
	public void setRepairBy(int repairBy) {
		this.repairBy = repairBy;
	}
	public Date getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

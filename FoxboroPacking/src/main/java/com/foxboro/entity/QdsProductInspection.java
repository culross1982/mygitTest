package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QdsProductInspection extends Base{
	private int partNo;
	private int inspectionBy;
	private int	inspectionStatus;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date inspectionTime;
	
	
	public int getInspectionBy() {
		return inspectionBy;
	}
	public void setInspectionBy(int inspectionBy) {
		this.inspectionBy = inspectionBy;
	}
	public int getInspectionStatus() {
		return inspectionStatus;
	}
	public void setInspectionStatus(int inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
	public Date getInspectionTime() {
		return inspectionTime;
	}
	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	public int getPartNo() {
		return partNo;
	}
	public void setPartNo(int partNo) {
		this.partNo = partNo;
	}
	
}

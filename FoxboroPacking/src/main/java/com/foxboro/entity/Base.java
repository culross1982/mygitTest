package com.foxboro.entity;

public class Base {
	private int id;
	private String dateStart;	//起始日期
	private String dateEnd;		//截止日期
	private int beginNo;
	private int pageSize;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getBeginNo() {
		return beginNo;
	}
	public void setBeginNo(int beginNo) {
		this.beginNo = beginNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

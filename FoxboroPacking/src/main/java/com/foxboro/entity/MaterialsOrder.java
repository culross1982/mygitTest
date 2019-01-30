package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MaterialsOrder{
	private int id;
	private String pwd;
	private int pwdQuantity;
	private String materialsName;
	private int materialsNum;
	private String materialsCategoryName;
	private int orderBy;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
	private long code;	//时间戳
	private int margin;	//余量
	
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMaterialsCategoryName() {
		return materialsCategoryName;
	}
	public void setMaterialsCategoryName(String materialsCategoryName) {
		this.materialsCategoryName = materialsCategoryName;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	
	public int getPwdQuantity() {
		return pwdQuantity;
	}
	public void setPwdQuantity(int pwdQuantity) {
		this.pwdQuantity = pwdQuantity;
	}
	public String getMaterialsName() {
		return materialsName;
	}
	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	public int getMaterialsNum() {
		return materialsNum;
	}
	public void setMaterialsNum(int materialsNum) {
		this.materialsNum = materialsNum;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
}

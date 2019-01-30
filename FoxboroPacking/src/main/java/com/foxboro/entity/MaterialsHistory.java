package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MaterialsHistory extends Base{
	private int id;
	private int materialsId;
	private int materialsChangeNum;
	private int materialStatusId;
	@DateTimeFormat(pattern="yyyy-MM-hh HH:mm:ss")
	private Date modifyDate;
	private int modifyBy;
	private int productId;
	private String remark;

	private String materialsName;
	private String realname;
	private String pwd;
	private String materialsCategoryName;
	private String productCategoryName;
	private String materialStatusName;

	
	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getMaterialStatusName() {
		return materialStatusName;
	}

	public void setMaterialStatusName(String materialStatusName) {
		this.materialStatusName = materialStatusName;
	}

	public String getMaterialsCategoryName() {
		return materialsCategoryName;
	}

	public void setMaterialsCategoryName(String materialsCategoryName) {
		this.materialsCategoryName = materialsCategoryName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getMaterialsId() {
		return materialsId;
	}

	public void setMaterialsId(int materialsId) {
		this.materialsId = materialsId;
	}

	public int getMaterialsChangeNum() {
		return materialsChangeNum;
	}

	public void setMaterialsChangeNum(int materialsChangeNum) {
		this.materialsChangeNum = materialsChangeNum;
	}

	public int getMaterialStatusId() {
		return materialStatusId;
	}

	public void setMaterialStatusId(int materialStatusId) {
		this.materialStatusId = materialStatusId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}

package com.foxboro.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Materials {
	private int id;
	private String materialsName;
	private int materialsNum;
	private int materialsCategoryNameId;//多对1
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	private String createdByUsername;
	private String modifyByUsername;
	private String materialsCategoryName;
	
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getMaterialsCategoryName() {
		return materialsCategoryName;
	}
	public void setMaterialsCategoryName(String materialsCategoryName) {
		this.materialsCategoryName = materialsCategoryName;
	}
	public int getMaterialsCategoryNameId() {
		return materialsCategoryNameId;
	}
	public void setMaterialsCategoryNameId(int materialsCategoryNameId) {
		this.materialsCategoryNameId = materialsCategoryNameId;
	}
	public String getCreatedByUsername() {
		return createdByUsername;
	}
	public void setCreatedByUsername(String createdByUsername) {
		this.createdByUsername = createdByUsername;
	}
	public String getModifyByUsername() {
		return modifyByUsername;
	}
	public void setModifyByUsername(String modifyByUsername) {
		this.modifyByUsername = modifyByUsername;
	}
	/*public Users getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}
	public Users getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Users modifyBy) {
		this.modifyBy = modifyBy;*/
	//}
	/*public MaterialsCategory getMaterialsCategoryNameId() {
		return materialsCategoryNameId;
	}
	public void setMaterialsCategoryNameId(MaterialsCategory materialsCategoryNameId) {
		this.materialsCategoryNameId = materialsCategoryNameId;
	}*/
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}

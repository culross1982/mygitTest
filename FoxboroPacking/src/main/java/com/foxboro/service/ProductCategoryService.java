package com.foxboro.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.ProductCategory;

public interface ProductCategoryService {
	/**
	 * 产品分类列表
	 * @return
	 */
	public List<ProductCategory> getCateList(); 
	
	/**
	 * 新增产品分类
	 * @param productCategoryName
	 * @return 0表示入参为"null"或为"空"， -1表示入参和已有数据库中名字重复，1表示新增正常
	 */
	public int addCate(String productCategoryName);
	
	/**
	 * 根据id修改产品分类
	 * @param id
	 */
	public void updataProCategory(int id);
	
	/**
	 * 根据id删除产品分类
	 * @param id
	 */
	public void delProCategory(int id);

}

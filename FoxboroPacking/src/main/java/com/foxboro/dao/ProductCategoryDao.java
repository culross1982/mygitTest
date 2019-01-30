package com.foxboro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 产品分类列表
	 * @return
	 */
	public List<ProductCategory> getCateList(); 
	
	/**
	 * 新增产品分类
	 * @param productCategoryName
	 * @return
	 */
	public void addCate(@Param("productCategoryName")String productCategoryName);
	
	/**
	 * 根据id修改产品分类
	 * @param id
	 */
	public void updataProCategory(@Param("id")int id);
	
	/**
	 * 根据id删除产品分类
	 * @param id
	 */
	public void delProCategory(@Param("id")int id);
}

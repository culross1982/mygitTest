package com.foxboro.dao;

import java.util.List;

import com.foxboro.entity.MaterialsCategory;

public interface MaterialsCategoryDao {
	/**
	 * 查询所有材料信息
	 * @return
	 */
	public List<MaterialsCategory> getMaterialsCategoryList();
	
	/**
	 * 根据材料名称查类别名称
	 * @return
	 */
	public String getMaterialsCategoryByMaterialsName(String materialsName);
}

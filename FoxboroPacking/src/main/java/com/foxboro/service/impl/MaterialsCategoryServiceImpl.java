package com.foxboro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.MaterialsCategoryDao;
import com.foxboro.entity.MaterialsCategory;
import com.foxboro.service.MaterialsCategoryService;
@Service
public class MaterialsCategoryServiceImpl implements MaterialsCategoryService {
	@Autowired
	private MaterialsCategoryDao materialsCategoryDao;
	
	//查询所有材料信息
	@Override
	public List<MaterialsCategory> getMaterialsCategoryList() {
		// TODO Auto-generated method stub
		return materialsCategoryDao.getMaterialsCategoryList();
	}

	//根据材料名称查类别名称
	@Override
	public String getMaterialsCategoryByMaterialsName(String materialsName) {
		// TODO Auto-generated method stub
		return materialsCategoryDao.getMaterialsCategoryByMaterialsName(materialsName);
	}

}

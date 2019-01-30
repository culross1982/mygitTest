package com.foxboro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.MaterialsOrderDao;
import com.foxboro.entity.MaterialsOrder;
import com.foxboro.service.MaterialsOrderService;
@Service
public class MaterialsOrderServiceImpl implements MaterialsOrderService {
	@Autowired
	private MaterialsOrderDao materialsOrderDao;

	//增加订购包材
	@Override
	public void addMaterialsOrder(MaterialsOrder materialsOrder) throws Exception{
		// TODO Auto-generated method stub
		materialsOrderDao.addMaterialsOrder(materialsOrder);
	}

	//根据材料名称判断订购表单里是否有此材料
	@Override
	public int materialsIsExsit(String materialsName) throws Exception {
		// TODO Auto-generated method stub
		return materialsOrderDao.materialsIsExsit(materialsName);
	}

	//根据材料名称修改数量
	@Override
	public void updateMaterialsOrder(String name, int num) throws Exception {
		// TODO Auto-generated method stub
		materialsOrderDao.updateMaterialsOrder(name, num);
	}

}

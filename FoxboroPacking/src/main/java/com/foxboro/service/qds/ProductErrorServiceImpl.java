package com.foxboro.service.qds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ProductErrorDao;
import com.foxboro.entity.QdsProductError;

@Service
public class ProductErrorServiceImpl implements ProductErrorService {
	@Autowired
	private ProductErrorDao productErrorDao;
	
	//新增产品不良记录
	@Override
	public void addProductError(QdsProductError qdsProductError) throws Exception {
		// TODO Auto-generated method stub
		productErrorDao.addProductError(qdsProductError);
	}

	//修改维修数据
	@Override
	public void updateProductError(QdsProductError qdsProductError) throws Exception {
		// TODO Auto-generated method stub
		productErrorDao.updateProductError(qdsProductError);
	}

}

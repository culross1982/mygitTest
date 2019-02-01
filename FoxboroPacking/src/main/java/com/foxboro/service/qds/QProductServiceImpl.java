package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.QProductDao;
import com.foxboro.entity.QdsProduct;

@Service
public class QProductServiceImpl implements QProductService {
	@Autowired
	private QProductDao qProductDao;
	
	//根据moduelNo查询是否存在product
	@Override
	public Integer isExsitProductByModuleNo(String moduleNo, String qdsProCategoryName) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.isExsitProductByModuleNo(moduleNo, qdsProCategoryName);
	}

	//新增QDS产品
	@Override
	public void addProduct(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		qProductDao.addProduct(qdsProduct);
	}

	//根据id删除对应的产品
	@Override
	public void delProduct(int id) throws Exception {
		// TODO Auto-generated method stub
		qProductDao.delProduct(id);
	}

	//按条件查询QDS产品
	@Override
	public List<QdsProduct> getQdsProduct(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProduct(qdsProduct);
	}

	//按条件查询QDS产品总数
	@Override
	public int getQdsProductCount(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductCount(qdsProduct);
	}

	//根据id获取单条QdsProduct记录
	@Override
	public QdsProduct getQdsProductById(int id) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductById(id);
	}

	//根据moduleNo修改对应的assyStatus
	@Override
	public void updateAssyStatusQdsProductByModuleNo(String moduleNo, int assyStatus) throws Exception {
		// TODO Auto-generated method stub
		qProductDao.updateAssyStatusQdsProductByModuleNo(moduleNo, assyStatus);
	}

}

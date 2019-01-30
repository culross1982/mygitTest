package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ProductOrderDao;
import com.foxboro.entity.QdsProductOrder;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
	@Autowired
	private ProductOrderDao productOrderDao;
	
	//按条件查询QDS工作令
	@Override
	public List<QdsProductOrder> getQdsProductOrder(QdsProductOrder qdsProductOrder) throws Exception {
		// TODO Auto-generated method stub
		return productOrderDao.getQdsProductOrder(qdsProductOrder);
	}

	//按条件查询QDS工作令总数
	@Override
	public int getQdsProductOrderCount(QdsProductOrder qdsProductOrder) throws Exception {
		// TODO Auto-generated method stub
		return productOrderDao.getQdsProductOrderCount(qdsProductOrder);
	}

	//添加产品工作令
	@Override
	public void addProductOrder(QdsProductOrder qdsProductOrder) throws Exception {
		// TODO Auto-generated method stub
		productOrderDao.addProductOrder(qdsProductOrder);
	}

	//根据order获取对应的id
	@Override
	public int getProductOrderIdByOrder(String order) throws Exception {
		// TODO Auto-generated method stub
		return productOrderDao.getProductOrderIdByOrder(order);
	}

	//根据snEnd模糊查询productOrder的最大值
	@Override
	public String getProductOrderBySnEnd(String snEnd, String qdsProCategoryName) throws Exception {
		// TODO Auto-generated method stub
		return productOrderDao.getProductOrderBySnEnd(snEnd, qdsProCategoryName);
	}

	//根据id删除对应的productOrder
	@Override
	public void delProductOrderById(int id) throws Exception {
		// TODO Auto-generated method stub
		productOrderDao.delProductOrderById(id);
	}
	

	
}

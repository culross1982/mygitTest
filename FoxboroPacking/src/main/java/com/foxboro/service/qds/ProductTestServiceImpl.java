package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ProductTestDao;
import com.foxboro.entity.QdsProductAssy;
import com.foxboro.entity.QdsProductTest;

@Service
public class ProductTestServiceImpl implements ProductTestService {
	@Autowired
	private ProductTestDao productTestDao;
	
	//按条件查询QDS测试产品
	@Override
	public List<QdsProductTest> getQProTest(QdsProductTest qProTest) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.getQProTest(qProTest);
	}

	//按条件查询QDS测试产品总数
	@Override
	public int getQProTestCount(QdsProductTest qProTest) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.getQProTestCount(qProTest);
	}

	//新增测试数据
	@Override
	public void addTestData(QdsProductTest qProTest) throws Exception {
		// TODO Auto-generated method stub
		productTestDao.addTestData(qProTest);
	}

}

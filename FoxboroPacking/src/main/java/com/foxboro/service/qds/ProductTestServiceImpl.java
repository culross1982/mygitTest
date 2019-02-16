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

	//根据moduleNo查询最后一条记录是否为PASS
	@Override
	public String isTestPassAtTheEnd(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.isTestPassAtTheEnd(moduleNo, qdsProCategoryId);
	}

	//根据moduleNo查询是否有记录为FAIL
	@Override
	public Integer isTestFailedByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.isTestFailedByModuleNo(moduleNo, qdsProCategoryId);
	}

	//设置PASS产品最后1条FAIL的记录的errorStauts为-1，表示有维修记录待输入
	@Override
	public void updateErrorStatusAtTheEnd(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		productTestDao.updateErrorStatusAtTheEnd(moduleNo, qdsProCategoryId);
	}

	//根据id修改errorStatus
	@Override
	public void updateProductTestById(int id, int errorStatus, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		productTestDao.updateProductTestById(id, errorStatus, qdsProCategoryId);
	}

	//根据moduleNo查询是否存在测试数据
	@Override
	public Integer isExistTestByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.isExistTestByModuleNo(moduleNo, qdsProCategoryId);
	}

	//根据moduleNo查询最后一条PASS的测试数据
	@Override
	public QdsProductTest getQdsProTestByTestAtEnd(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.getQdsProTestByTestAtEnd(moduleNo, qdsProCategoryId);
	}

	//根据moduleNo查询最后一条FAIL的测试数据
	@Override
	public QdsProductTest getQdsProTestByTestFailAtEnd(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return productTestDao.getQdsProTestByTestFailAtEnd(moduleNo, qdsProCategoryId);
	}

}

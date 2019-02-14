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
	public Integer isExsitProductByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.isExsitProductByModuleNo(moduleNo, qdsProCategoryId);
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
	
	//根据moduleNo修改对应的testStatus
	@Override
	public void updateTestStatusQdsProductByModuleNo(String moduleNo, int testStatus) throws Exception {
		// TODO Auto-generated method stub
		qProductDao.updateTestStatusQdsProductByModuleNo(moduleNo, testStatus);
	}

	//按条件查询DQS中TestResult有值的产品
	@Override
	public List<QdsProduct> getQdsProductWhereTestResult(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductWhereTestResult(qdsProduct);
	}

	//按条件查询QDS中TestResult有值的产品总数
	@Override
	public int getQdsProductCountWhereTestResult(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductCountWhereTestResult(qdsProduct);
	}

	//按moduleNo查看装配数据是否PASS
	@Override
	public Integer getAssyStatusByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getAssyStatusByModuleNo(moduleNo, qdsProCategoryId);
	}

	//按id查看是此工作令否已有装配数据
	@Override
	public List<Integer> getAssyStatusById(int id, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getAssyStatusById(id, qdsProCategoryId);
	}

	//按条件查询可以检验的QDS产品
	@Override
	public List<QdsProduct> getQdsProductByInspect(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductByInspect(qdsProduct);
	}

	//按条件查询可以检验的QDS产品总数
	@Override
	public int getQdsProductCountByInspect(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductCountByInspect(qdsProduct);
	}

	//根据moduleNo查找对应的qdsProduct
	@Override
	public QdsProduct getQdsProductByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProductByModuleNo(moduleNo, qdsProCategoryId);
	}

	//根据inspectionStatus修改inspect信息
	@Override
	public void updateQdsProInsByModuleNo(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		qProductDao.updateQdsProInsByModuleNo(qdsProduct);
	}
	
	//按日期获取检验工作令id
	@Override
	public List<Integer> getQdsProOrderIdByInspectionTime(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getQdsProOrderIdByInspectionTime(qdsProduct);
	}

	//获取当天已检验的数量
	@Override
	public int getCountByQdsProOrderId(QdsProduct qdsProduct) throws Exception {
		// TODO Auto-generated method stub
		return qProductDao.getCountByQdsProOrderId(qdsProduct);
	}

	

}

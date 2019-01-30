package com.foxboro.service.qds;

import java.util.List;

import com.foxboro.entity.QdsProductAssy;
import com.foxboro.entity.QdsProductTest;

public interface ProductTestService {
	/**
	 * 按条件查询QDS测试产品
	 * @param qProTest
	 * @return
	 * @throws Exception
	 */
	public List<QdsProductTest> getQProTest(QdsProductTest qProTest) throws Exception;
	
	/**
	 * 按条件查询QDS测试产品总数
	 * @param qProTest
	 * @return
	 * @throws Exception
	 */
	public int getQProTestCount(QdsProductTest qProTest) throws Exception;
	
	/**
	 * 新增测试数据
	 * @param qProAssy
	 */
	public void addTestData(QdsProductTest qProTest) throws Exception;
}

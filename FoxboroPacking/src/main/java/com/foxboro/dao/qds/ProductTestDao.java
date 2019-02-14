package com.foxboro.dao.qds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.QdsProductAssy;
import com.foxboro.entity.QdsProductTest;

public interface ProductTestDao {
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
	
	/**
	 * 根据moduleNo查询最后一条记录是否为PASS
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @return
	 * @throws Exception
	 */
	public String isTestPassAtTheEnd(@Param("moduleNo") String moduleNo,
							         @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 根据moduleNo查询是否有记录为FAIL
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @return
	 * @throws Exception
	 */
	public Integer isTestFailedByModuleNo(@Param("moduleNo") String moduleNo,
							              @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 设置PASS产品最后1条FAIL的记录的errorStauts为-1，表示有维修记录待输入
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @throws Exception
	 */
	public void updateErrorStatusAtTheEnd(@Param("moduleNo") String moduleNo,
										  @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 根据id修改errorStatus
	 * @param id
	 * @param errorStatus
	 * @param qdsProCategoryId
	 * @throws Exception
	 */
	public void updateProductTestById(@Param("id") int id,
									  @Param("errorStatus") int errorStatus,
									  @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 根据moduleNo查询是否存在测试数据
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @return
	 * @throws Exceptione
	 */
	public Integer isExistTestByModuleNo(@Param("moduleNo") String moduleNo,
									 @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
}

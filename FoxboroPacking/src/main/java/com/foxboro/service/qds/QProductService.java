package com.foxboro.service.qds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.QdsProduct;

public interface QProductService {

	/**
	 * 根据moduelNo查询是否存在product
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @return
	 * @throws Exception
	 */
	public Integer isExsitProductByModuleNo(String moduleNo,int qdsProCategoryId) throws Exception;
	
	/**
	 * 新增QDS产品
	 * @param qdsProduct
	 * @return
	 * @throws Exception
	 */
	public void addProduct(QdsProduct qdsProduct)throws Exception;
	
	/**
	 * 根据id删除对应的产品
	 * @param id
	 * @throws Excption
	 */
	public void delProduct(int id) throws Exception;
	
	/**
	 * 按条件查询QDS产品
	 * @param qdsProduct 产品条件
	 * @param beginNo	起始行数
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<QdsProduct> getQdsProduct(QdsProduct qdsProduct) throws Exception;
	
	/**
	 * 按条件查询QDS产品总数
	 * @param qdsProduct
	 * @return
	 */
	public int getQdsProductCount(QdsProduct qdsProduct) throws Exception;
	
	/**
	 * 根据id获取单条QdsProduct记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QdsProduct getQdsProductById(int id) throws Exception;
	
	/**
	 * 根据moduleNo修改对应的assyStatus
	 * @param moduleNo
	 * @throws Exception
	 */
	public void updateAssyStatusQdsProductByModuleNo(String moduleNo,int assyStatus) throws Exception;
	
	/**
	 * 根据moduleNo修改对应的testStatus
	 * @param moduleNo
	 * @throws Exception
	 */
	public void updateTestStatusQdsProductByModuleNo(String moduleNo,int testStatus) throws Exception;
	
	/**
	 * 按条件查询DQS中TestResult有值的产品
	 * @param qdsProduct 产品条件
	 * @param beginNo	起始行数
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<QdsProduct> getQdsProductWhereTestResult(QdsProduct qdsProduct) throws Exception;
	
	/**
	 * 按条件查询QDS中TestResult有值的产品总数
	 * @param qdsProduct
	 * @return
	 */
	public int getQdsProductCountWhereTestResult(QdsProduct qdsProduct) throws Exception;
	
	/**
	 * 按moduleNo查看装配数据是否PASS
	 * @param moduleNo
	 * @return
	 * @throws Exception
	 */
	public Integer getAssyStatusByModuleNo(@Param("moduleNo") String moduleNo,
										   @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
}

package com.foxboro.service.qds;

import java.util.List;

import com.foxboro.entity.QdsProduct;

public interface QProductService {

	/**
	 * 根据moduelNo查询是否存在product
	 * @param moduleNo
	 * @param qdsProCategoryName
	 * @return
	 * @throws Exception
	 */
	public Integer isExsitProductByModuleNo(String moduleNo,String qdsProCategoryName) throws Exception;
	
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
}

package com.foxboro.dao.qds;

import com.foxboro.entity.QdsProductError;

public interface ProductErrorDao {
	/**
	 * 新增产品不良记录
	 * @param qdsProductError
	 * @throws Exception
	 */
	public void addProductError(QdsProductError qdsProductError) throws Exception;
	
	/**
	 * 修改维修数据
	 * @param qdsProductError
	 * @throws Exception
	 */
	public void updateProductError(QdsProductError qdsProductError) throws Exception;
}

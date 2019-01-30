package com.foxboro.dao.qds;

import java.util.List;

import com.foxboro.entity.QdsErrorCode;

public interface ErrorCodeDao {
	/**
	 * 获取所有的维修代码
	 * @return
	 * @throws Exception
	 */
	public List<QdsErrorCode> getAllErrorCode() throws Exception;
}

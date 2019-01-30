package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ErrorCodeDao;
import com.foxboro.entity.QdsErrorCode;

@Service
public class ErrorCodeServiceImpl implements ErrorCodeService {
	@Autowired
	private ErrorCodeDao errorCodeDao;
	
	//获取所有的维修代码
	@Override
	public List<QdsErrorCode> getAllErrorCode() throws Exception {
		// TODO Auto-generated method stub
		return errorCodeDao.getAllErrorCode();
	}

}

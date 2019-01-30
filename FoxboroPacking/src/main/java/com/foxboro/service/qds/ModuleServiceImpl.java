package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ModuleDao;
import com.foxboro.entity.QdsModule;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;
	
	//按条件查询QDS基础数据
	@Override
	public List<QdsModule> getQProModule(QdsModule qdsModule) throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.getQProModule(qdsModule);
	}

	//按条件查询QDS基础数据总数
	@Override
	public int getQProModuleCount(QdsModule qdsModule) throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.getQProModuleCount(qdsModule);
	}

	//新增基础数据
	@Override
	public void addQModule(QdsModule qdsModule) throws Exception {
		// TODO Auto-generated method stub
		moduleDao.addQModule(qdsModule);
	}

	//查询是否有指定模块型号
	@Override
	public QdsModule searchModule(QdsModule qdsModule) throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.searchModule(qdsModule);
	}

	//根据id删除指定基础数据
	@Override
	public void delModule(int id) throws Exception {
		// TODO Auto-generated method stub
		moduleDao.delModule(id);
	}

	//根据module获取ver
	@Override
	public String getVerByModule(String module,String qdsProCategoryName) throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.getVerByModule(module,qdsProCategoryName);
	}

}

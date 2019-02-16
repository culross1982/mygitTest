package com.foxboro.service.qds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.qds.ProductAssyDao;
import com.foxboro.entity.QdsProductAssy;

@Service
public class ProductAssyServiceImpl implements ProductAssyService {
	@Autowired
	private ProductAssyDao qdsProductAssyDao;

	//按条件分页查询QDS装配产品
	@Override
	public List<QdsProductAssy> getQProAssy(QdsProductAssy qProAssy) throws Exception{
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getQProAssy(qProAssy);
	}

	//按条件查询QDS装配产品总数
	@Override
	public int getQProAssyCount(QdsProductAssy qProAssy) throws Exception{
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getQProAssyCount(qProAssy);
	}

	//获取所有装配产品记录ASC
	@Override
	public List<QdsProductAssy> getQProAssyAsc(QdsProductAssy qProAssy) throws Exception{
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getQProAssyAsc(qProAssy);
	}

	//查询数据是否存在
	@Override
	public int getAssyDataIsExsit(QdsProductAssy qProAssy) throws Exception {
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getAssyDataIsExsit(qProAssy);
	}

	//新增装配数据
	@Override
	public void addAssyData(QdsProductAssy qProAssy) throws Exception {
		// TODO Auto-generated method stub
		qdsProductAssyDao.addAssyData(qProAssy);
	}

	//按id删除装配数据
	@Override
	public void delAssyDataById(int id) throws Exception {
		// TODO Auto-generated method stub
		qdsProductAssyDao.delAssyDataById(id);
	}

	//按id查询单个装配数据
	@Override
	public QdsProductAssy getAssyDataById(int id) throws Exception {
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getAssyDataById(id);
	}

	//更新装配数据
	@Override
	public void updateProductAssy(QdsProductAssy qProAssy) throws Exception {
		// TODO Auto-generated method stub
		qdsProductAssyDao.updateProductAssy(qProAssy);
	}

	//按moduleNo查询QDS装配产品的单板个数
	@Override
	public int getQProAssyCountByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getQProAssyCountByModuleNo(moduleNo, qdsProCategoryId);
	}

	//按moduleNo查询对应的assyNo
	@Override
	public String getAssyNoByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getAssyNoByModuleNo(moduleNo, qdsProCategoryId);
	}

	//按moduleNo查询对应的QdsProductAssy
	@Override
	public List<QdsProductAssy> getQdsProductAssyByModuleNo(String moduleNo, int qdsProCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return qdsProductAssyDao.getQdsProductAssyByModuleNo(moduleNo, qdsProCategoryId);
	}

	

}

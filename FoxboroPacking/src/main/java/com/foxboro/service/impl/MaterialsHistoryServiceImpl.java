package com.foxboro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.MaterialsHistoryDao;
import com.foxboro.entity.MaterialsHistory;
import com.foxboro.service.MaterialsHistoryService;

@Service
public class MaterialsHistoryServiceImpl implements MaterialsHistoryService {
	@Autowired
	private MaterialsHistoryDao materialsHistoryDao;
	
	//添加包材记录
	@Override
	public void addHistory(MaterialsHistory history) {
		// TODO Auto-generated method stub
		if(history!=null){
			try {
				materialsHistoryDao.addHistory(history);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//获取所有包材历史记录desc
	@Override
	public List<MaterialsHistory> getAllMaterialsHistoryList(int beginNo,int pageSize,String dateStart,String dateEnd) {
		// TODO Auto-generated method stub
		return materialsHistoryDao.getAllMaterialsHistoryList(beginNo,pageSize,dateStart,dateEnd);
	}
	
	//获取所有包材历史记录asc
		@Override
		public List<MaterialsHistory> getAllMaterialsHistoryListAsc(int beginNo,int pageSize) {
			// TODO Auto-generated method stub
			return materialsHistoryDao.getAllMaterialsHistoryListAsc(beginNo,pageSize);
		}

	//获取所有包材历史记录条数
	@Override
	public int getMaterialsHistoryCount(MaterialsHistory materialsHistory) {
		// TODO Auto-generated method stub
		return materialsHistoryDao.getMaterialsHistoryCount(materialsHistory);
	}

	//根据modifyBy查找是否有包材历史记录
	@Override
	public int getMaterialsHistoryByModify(int id) {
		// TODO Auto-generated method stub
		return materialsHistoryDao.getMaterialsHistoryByModify(id);
	}

	//根据pwd查找是否有包材历史记录
	@Override
	public int getCountByPwd(String pwd) {
		// TODO Auto-generated method stub
		return materialsHistoryDao.getCountByPwd(pwd);
	}

	//根据材料名查询历史记录中的状态
	@Override
	public ArrayList<Integer> getMaterialsHistoryStatus(String materialsName) {
		// TODO Auto-generated method stub
		return materialsHistoryDao.getMaterialsHistoryStatus(materialsName);
	}

	//根据materialsName删除历史记录中的单条记录
	@Override
	public void delMaterialsHistory(String materialsName) {
		// TODO Auto-generated method stub
		materialsHistoryDao.delMaterialsHistory(materialsName);
	}

	//根据id删除历史记录中的单条记录
	@Override
	public void delMaterialsHistoryById(int id) throws Exception {
		// TODO Auto-generated method stub
		materialsHistoryDao.delMaterialsHistoryById(id);
	}

	
}

package com.foxboro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.foxboro.entity.MaterialsHistory;

public interface MaterialsHistoryService {
	/**
	 * 添加包材记录
	 * @param history
	 * @return
	 */
	public void addHistory(MaterialsHistory history);
	
	/**
	 * 获取所有包材历史记录DESC
	 * @return
	 */
	public List<MaterialsHistory> getAllMaterialsHistoryList(int beginNo,int pageSize,String dateStart,String dateEnd);
	
	/**
	 * 获取所有包材历史记录ASC
	 * @return
	 */
	public List<MaterialsHistory> getAllMaterialsHistoryListAsc(int beginNo,int pageSize);
	
	/**
	 * 获取所有包材历史记录条数
	 * @return
	 */
	public int getMaterialsHistoryCount(MaterialsHistory materialsHistory);
	
	/**
	 * 根据modifyBy查找是否有包材历史记录
	 * @param id
	 * @return
	 */
	public int getMaterialsHistoryByModify(int id);
	
	/**
	 * 根据pwd查找是否有包材历史记录
	 * @param pwd
	 * @return
	 */
	public int getCountByPwd(String pwd);
	
	/**
	 * 根据pwd查询历史记录中的状态
	 * @param materialsName 材料名
	 * @return 状态集合
	 */
	public ArrayList<Integer> getMaterialsHistoryStatus(String materialsName);
	
	/**
	 * 根据materialsName删除历史记录中的单条记录
	 * @param materialsName
	 */
	public void delMaterialsHistory(String materialsName);
	
	/**
	 * 根据id删除历史记录中的单条记录
	 * @param materialsName
	 */
	public void delMaterialsHistoryById(int id)throws Exception;
}

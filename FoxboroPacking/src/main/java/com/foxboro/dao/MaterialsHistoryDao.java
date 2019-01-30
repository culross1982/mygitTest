package com.foxboro.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.MaterialsHistory;

public interface MaterialsHistoryDao {
	/**
	 * 添加包材记录
	 * @param history
	 * @return
	 */
	public void addHistory(@Param("materialsHistory")MaterialsHistory materialsHistory);
	
	/**
	 * 获取所有包材历史记录DESC
	 * @param beginNo 起始行号
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<MaterialsHistory> getAllMaterialsHistoryList(@Param("beginNo")int beginNo,
															 @Param("pageSize")int pageSize,
															 @Param("dateStart")String dateStart,
															 @Param("dateEnd")String dateEnd);
	
	/**
	 * 获取所有包材历史记录ASC
	 * @return
	 */
	public List<MaterialsHistory> getAllMaterialsHistoryListAsc(@Param("beginNo")int beginNo,
															 @Param("pageSize")int pageSize);
	
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
	public int getMaterialsHistoryByModify(@Param("modifyBy")int id);
	
	/**
	 * 根据pwd查找是否有包材历史记录
	 * @param String
	 * @return
	 */
	public int getCountByPwd(@Param("pwd")String pwd);
	
	/**
	 * 根据materialsName查询历史记录中的状态
	 * @param materialsName 材料名
	 * @return 状态集合
	 */
	public ArrayList<Integer> getMaterialsHistoryStatus(@Param("materialsName")String materialsName);
	
	/**
	 * 根据materialsName删除历史记录中的单条记录
	 * @param materialsName
	 */
	public void delMaterialsHistory(@Param("materialsName")String materialsName);
	
	/**
	 * 根据id删除历史记录中的单条记录
	 * @param materialsName
	 */
	public void delMaterialsHistoryById(int id)throws Exception;
}

package com.foxboro.dao.qds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.QdsModule;

public interface ModuleDao {
	/**
	 * 按条件查询QDS基础数据
	 * @param qdsModule	基础数据条件
	 * @param beginNo	起始行数
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<QdsModule> getQProModule(QdsModule qdsModule) throws Exception;
	
	/**
	 * 按条件查询QDS基础数据总数
	 * @param qProAssy
	 * @return
	 */
	public int getQProModuleCount(QdsModule qdsModule) throws Exception;
	
	/**
	 * 新增基础数据
	 * @param qdsModule
	 * @throws Exception
	 */
	public void addQModule(QdsModule qdsModule) throws Exception;
	
	/**
	 * 查询是否有指定模块型号
	 * @param qdsModule
	 * @return	QdsModule
	 * @throws Exception
	 */
	public QdsModule searchModule(QdsModule qdsModule) throws Exception;
	
	/**
	 * 根据id删除指定基础数据
	 * @param id
	 * @throws Exception
	 */
	public void delModule(int id) throws Exception;
	
	/**
	 * 根据module获取ver
	 * @param module
	 * @param qdsProCategoryName
	 * @return
	 * @throws Exception
	 */
	public String getVerByModule(@Param("module") String module,
								 @Param("qdsProCategoryName") String qdsProCategoryName) throws Exception;
	
}

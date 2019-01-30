package com.foxboro.dao.qds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.QdsProductOrder;

public interface ProductOrderDao {
	/**
	 * 按条件查询QDS工作令
	 * @param qdsProductOrder 工作令条件条件
	 * @param beginNo	起始行数
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<QdsProductOrder> getQdsProductOrder(QdsProductOrder qdsProductOrder) throws Exception;
	
	/**
	 * 按条件查询QDS工作令总数
	 * @param qProAssy
	 * @return
	 */
	public int getQdsProductOrderCount(QdsProductOrder qdsProductOrder) throws Exception;
	
	/**
	 * 添加产品工作令
	 * @param qdsProductOrder
	 * @throws Exception
	 */
	public void addProductOrder(QdsProductOrder qdsProductOrder) throws Exception;
	
	/**
	 * 根据order获取对应的id
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int getProductOrderIdByOrder(String order) throws Exception;
	
	/**
	 * 根据snEnd模糊查询productOrder的最大值
	 * @param snEnd
	 * @param qdsProCategoryName
	 * @return
	 * @throws Exception
	 */
	public String getProductOrderBySnEnd(@Param("snEnd")String snEnd,@Param("qdsProCategoryName")String qdsProCategoryName) throws Exception;
	
	/**
	 * 根据id删除对应的productOrder
	 * @param id
	 * @throws Exception
	 */
	public void delProductOrderById(int id) throws Exception;
}

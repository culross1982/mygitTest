package com.foxboro.dao;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.MaterialsOrder;

public interface MaterialsOrderDao {
	/**
	 * 增加订购包材
	 * @param materialsOrder
	 */
	public void addMaterialsOrder(MaterialsOrder materialsOrder) throws Exception;
	
	/**
	 * 根据材料名称判断订购表单里是否有此材料
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public int materialsIsExsit(String materialsName) throws Exception;
	
	/**
	 * 根据材料名称修改数量
	 * @param materialsNum
	 * @throws Exception
	 */
	public void updateMaterialsOrder(@Param("name") String name, 
									 @Param("num") int num) throws Exception;
}

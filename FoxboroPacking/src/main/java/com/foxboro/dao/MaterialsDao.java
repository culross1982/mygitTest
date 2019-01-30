package com.foxboro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.Materials;

public interface MaterialsDao {
	/**
	 * 材料清单
	 * @param materialsName
	 * @param beginNo
	 * @param pageSize
	 * @return
	 */
	public List<Materials> materialsList(@Param("materialsName")String materialsName,
							  @Param("beginNo")int beginNo,
							  @Param("pageSize")int pageSize);
	
	/**
	 * 材料总数
	 * @return
	 */
	public int countMaterials(@Param("materialsName")String materialsName);
	
	/**
	 * 手动增加材料
	 * @param Materials
	 */
	public void addMaterialsMT(@Param("materials")Materials materials);
	
	/**
	 * 根据id删除材料信息
	 * @param id
	 */
	public void doDelMaterials(@Param("id")int id);
	
	/**
	 * 修改材料信息
	 * @param materials
	 */
	public void updateMaterials(@Param("materials")Materials materials);
	
	/**
	 * 根据id查找单个材料
	 * @param id
	 * @return
	 */
	public Materials getMaterialsById(@Param("id")int id);
	
	/**
	 * 根据materialsName查找单个材料
	 * @param materialsName
	 * @return
	 */
	public Materials getMaterialsByName(@Param("materialsName")String materialsName);
	
	/**
	 * 材料入库
	 * @param inNum
	 * @return
	 */
	public void warehouseIn(@Param("inNum")int inNum,@Param("id")int id,@Param("modifyBy")int modifyBy);
	
	/**
	 * 查询所有材料名称
	 * @return
	 */
	public List<Materials> getAllMaterials();
	
	/**
	 * 材料出库
	 * @param inNum
	 * @return
	 */
	public void warehouseOut(@Param("inNum")int inNum,@Param("id")int id,@Param("modifyBy")int modifyBy);
	
	/**
	 * 根据材料名称查询材料库存数量
	 * @param materialsName
	 * @return
	 */
	public Integer getMaterialsNumByName(@Param("materialsName")String materialsName);
	
	/**
	 * 使用包材，更新包材数量
	 * @param materialsName 包材型号
	 * @param materialsNum	包材数量
	 */
	public void updataMaterialsNumByName(@Param("materialsName")String materialsName,
									     @Param("materialsNum")int materialsNum,
									     @Param("modifyBy")int modifyBy);
	
	/**
	 * 根据包材名称查id
	 * @param materialsName
	 * @return
	 */
	public int findIdByMaterialsName(@Param("materialsName")String materialsName);
	
	/**
	 * 根据id修改包材数量
	 * @param id
	 */
	public void updateMaterialsNumById(int id)throws Exception;
	
}

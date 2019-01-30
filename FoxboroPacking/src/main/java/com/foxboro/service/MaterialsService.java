package com.foxboro.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.Materials;
import com.foxboro.entity.Users;

public interface MaterialsService {
	
	/**
	 * 改变【材料信息】的同时新增【材料信息】至【包材历史清单】中的方法
	 * @param materialsName 材料型号
	 * @param id	材料id
	 * @param materialsNum 材料数量
	 * @param status  材料状态：0入库 1出库
	 * @param remark  备注
	 * @param user  操作者
	 */
	public void addHistory(String materialsName,int id,int materialsNum,int status,Users user,int productId,String remark);
	
	/**
	 * 根据页面输入数据判断是否更新包材【库存信息】
	 * @param materialsCategoryName	材料分类名称
	 * @param quantity	材料库存数量
	 * @param quantityMate	材料使用数量
	 * @return
	 */
	public List<String> result(String materialsCategoryName,int quantity,Integer quantityMate);
	
	/**
	 * 材料清单
	 * @param materialsName
	 * @param beginNo
	 * @param pageSize
	 * @return
	 */
	public List<Materials> materialsList(String materialsName,int beginNo,int pageSize);
	
	/**
	 * 材料总数
	 * @return
	 */
	public int countMaterials(String materialsName);
	
	/**
	 * 手动增加材料
	 * @param Materials
	 */
	public void addMaterialsMT(Materials materials);
	
	/**
	 * 根据id删除材料信息
	 * @param id
	 */
	public void doDelMaterials(int id);
	
	/**
	 * 修改材料信息
	 * @param materials
	 */
	public void updateMaterials(Materials materials);
	
	/**
	 * 根据id查找单个材料
	 * @param id
	 * @return
	 */
	public Materials getMaterialsById(int id);
	
	/**
	 * 根据materialsName查找单个材料
	 * @param id
	 * @return
	 */
	public Materials getMaterialsByName(String materialsName);
	
	/**
	 * 材料入库
	 * @param inNum
	 * @param id
	 * @param modifyBy
	 */
	public void warehouseIn(int inNum,int id,int modifyBy);
	
	/**
	 * 查询所有材料名称
	 * @return
	 */
	public List<Materials> getAllMaterials();
	
	/**
	 * 材料出库
	 * @param inNum
	 * @param id
	 * @param modifyBy
	 */
	public void warehouseOut(int inNum,int id,int modifyBy);
	
	/**
	 * 根据材料名称查询材料库存数量
	 * @param materialsName
	 * @return
	 */
	public Integer getMaterialsNumByName(String materialsName);
	
	/**
	 * 使用包材，更新包材数量
	 * @param materialsName 包材型号
	 * @param materialsNum	包材数量
	 * @param modifyBy	使用者
	 */
	public void updataMaterialsNumByName(String materialsName,int materialsNum,int modifyBy);
	
	/**
	 * 根据包材名称查id
	 * @param materialsName
	 * @return
	 */
	public int findIdByMaterialsName(String materialsName);
	
	/**
	 * 根据id修改包材数量
	 * @param id
	 */
	public void updateMaterialsNumById(int id)throws Exception;
}

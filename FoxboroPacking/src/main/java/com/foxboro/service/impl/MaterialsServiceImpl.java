package com.foxboro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foxboro.dao.MaterialsDao;
import com.foxboro.dao.MaterialsHistoryDao;
import com.foxboro.entity.Materials;
import com.foxboro.entity.MaterialsHistory;
import com.foxboro.entity.Users;
import com.foxboro.service.MaterialsService;
@Service
public class MaterialsServiceImpl implements MaterialsService {
	@Autowired
	private MaterialsDao materialsDao;
	@Autowired
	private MaterialsHistoryDao materialsHistoryDao;
	
	/**
	 * 改变【材料信息】的同时新增【材料信息】至【包材历史清单】中的方法
	 * @param materialsName 材料型号
	 * @param id	材料id
	 * @param materialsNum 材料数量
	 * @param status  材料状态：1新建 2入库 3出库 4 使用
	 * @param user  操作者
	 * @Param productId 产品id
	 * @param remark 备注
	 */
	public void addHistory(String materialsName,int id,int materialsNum,int status,Users user,int productId,String remark){
		MaterialsHistory history=new MaterialsHistory();
		//入参为materialsName或id
		int materialsId=0;
		if(materialsName!=null){
			materialsId=findIdByMaterialsName(materialsName);
		}else if(id!=0){
			materialsId=id;
		}
		history.setMaterialsId(materialsId);
		history.setMaterialsChangeNum(materialsNum);
		history.setMaterialStatusId(status);
		history.setModifyBy(user.getId());
		history.setProductId(productId);
		history.setRemark(remark);
		materialsHistoryDao.addHistory(history);
	}
	
	/**
	 * 根据页面输入数据判断是否更新包材【库存信息】
	 * @param materialsCategoryName	材料分类名称
	 * @param quantity	材料库存数量
	 * @param quantityMate	材料使用数量
	 * @return 
	 */
	public List<String> result(String materialsCategoryName,int quantity,Integer quantityMate){
		List<String> resultList=new ArrayList<String>();
		boolean flag=true;
		int materialsNumBox=0;
		if(materialsCategoryName!=null && !materialsCategoryName.equals("")){	//材料分类名称为null则不执行以下
			if(quantityMate!=null && quantityMate!=0){	//仓库中无此型号包材则不执行
				if(quantityMate>=quantity){	 //库存不够则不执行
					materialsNumBox=quantityMate-quantity;
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
		}else{
			materialsCategoryName="";
		}
		resultList.add(materialsCategoryName);//材料分类名称
		resultList.add(String.valueOf(materialsNumBox));//材料剩余数量
		resultList.add(String.valueOf(quantity));//材料使用数量
		resultList.add(String.valueOf(flag));//更新状态
		return resultList;
	}

	//材料清单
	@Override
	public List<Materials> materialsList(String materialsName, int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return materialsDao.materialsList(materialsName, beginNo, pageSize);
	}

	//材料总数
	@Override
	public int countMaterials(String materialsName) {
		// TODO Auto-generated method stub
		return materialsDao.countMaterials(materialsName);
	}

	//手动增加材料
	@Override
	public void addMaterialsMT(Materials materials) {
		// TODO Auto-generated method stub
		materialsDao.addMaterialsMT(materials);
	}

	//根据id删除材料信息
	@Override
	public void doDelMaterials(int id) {
		// TODO Auto-generated method stub
		materialsDao.doDelMaterials(id);
	}

	//修改材料信息
	@Override
	public void updateMaterials(Materials materials) {
		// TODO Auto-generated method stub
		materialsDao.updateMaterials(materials);
	}

	//根据id查找单个材料
	@Override
	public Materials getMaterialsById(int id) {
		// TODO Auto-generated method stub
		return materialsDao.getMaterialsById(id);
	}
	
	//根据materialsName查找单个材料的数量
	@Override
	public Materials getMaterialsByName(String materialsName) {
		// TODO Auto-generated method stub
		return materialsDao.getMaterialsByName(materialsName);
	}
	

	//材料入库
	@Override
	public void warehouseIn(int inNum, int id,int modifyBy) {
		// TODO Auto-generated method stub
		materialsDao.warehouseIn(inNum, id,modifyBy);
	}

	//查询所有材料名称
	@Override
	public List<Materials> getAllMaterials() {
		// TODO Auto-generated method stub
		return materialsDao.getAllMaterials();
	}

	//材料出库
	@Override
	public void warehouseOut(int inNum, int id,int modifyBy) {
		// TODO Auto-generated method stub
		try {
			materialsDao.warehouseOut(inNum, id,modifyBy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//根据材料名称查询材料库存数量
	@Override
	public Integer getMaterialsNumByName(String materialsName) {
		// TODO Auto-generated method stub
		if(materialsName!=null){
			return materialsDao.getMaterialsNumByName(materialsName);
		}else{
			return null;
		}
	}

	//使用包材，更新包材数量
	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updataMaterialsNumByName(String materialsName, int materialsNum,int modifiy) {
		// TODO Auto-generated method stub
		try {
			materialsDao.updataMaterialsNumByName(materialsName, materialsNum,modifiy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//根据包材名称查id
	@Override
	public int findIdByMaterialsName(String materialsName) {
		// TODO Auto-generated method stub
		int result=0;
		if(materialsName!=null){
			result= materialsDao.findIdByMaterialsName(materialsName);
		}
		return result;
	}

	//根据id修改包材数量
	@Override
	public void updateMaterialsNumById(int id) throws Exception{
		// TODO Auto-generated method stub
		materialsDao.updateMaterialsNumById(id);
	}

}

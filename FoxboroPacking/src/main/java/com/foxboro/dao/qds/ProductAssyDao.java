package com.foxboro.dao.qds;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.MaterialsHistory;
import com.foxboro.entity.QdsProductAssy;

public interface ProductAssyDao {
	/**
	 * 按条件查询QDS装配产品
	 * @param qProAssy	产品条件
	 * @param beginNo	起始行数
	 * @param pageSize	页数
	 * @param dateStart	开始日期
	 * @param dateEnd	结束日期
	 * @return
	 */
	public List<QdsProductAssy> getQProAssy(QdsProductAssy qProAssy) throws Exception;
	 
	/**
	 * 按条件查询QDS装配产品总数
	 * @param qProAssy
	 * @return
	 */
	public int getQProAssyCount(QdsProductAssy qProAssy) throws Exception;
	
	/**
	 * 获取所有装配产品记录ASC
	 * @return
	 */
	public List<QdsProductAssy> getQProAssyAsc(QdsProductAssy qProAssy) throws Exception;
	
	/**
	 * 查询数据是否存在
	 * @param qProAssy
	 * @return
	 */
	public int getAssyDataIsExsit(QdsProductAssy qProAssy) throws Exception;
	
	/**
	 * 新增装配数据
	 * @param qProAssy
	 * @throws Exception
	 */
	public void addAssyData(QdsProductAssy qProAssy)throws Exception;
	
	/**
	 * 按id删除装配数据
	 * @param id
	 * @throws Exception
	 */
	public void delAssyDataById(int id)throws Exception;
	
	/**
	 * 按id查询单个装配数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QdsProductAssy getAssyDataById(int id)throws Exception;
	
	/**
	 * 更新装配数据
	 * @param qProAssy
	 * @return
	 * @throws Exception
	 */
	public void updateProductAssy(QdsProductAssy qProAssy)throws Exception;
	
	/**
	 * 按moduleNo查询QDS装配产品的单板个数
	 * @param moduleNo
	 * @param qdsProCategoryId
	 * @return
	 * @throws Exception
	 */
	public int getQProAssyCountByModuleNo(@Param("moduleNo") String moduleNo,
										  @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 按moduleNo查询对应的assyNo
	 * @param moduleNo
	 * @return
	 * @throws Exception
	 */
	public String getAssyNoByModuleNo(@Param("moduleNo") String moduleNo,
									  @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
	
	/**
	 * 按moduleNo查询对应的QdsProductAssy
	 * @param moduleNo
	 * @return
	 * @throws Exception
	 */
	public List<QdsProductAssy> getQdsProductAssyByModuleNo(@Param("moduleNo") String moduleNo,
									  @Param("qdsProCategoryId") int qdsProCategoryId) throws Exception;
}

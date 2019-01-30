package com.foxboro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;

public interface ProductDao {
	
	/**
	 * 按条件查询包材信息
	 * @param pwd 产品型号
	 * @param box	包装盒
	 * @param gasket	衬垫
	 * @param spongiaOne	海绵（固定）
	 * @param spongiaTwo	海绵（变化）
	 * @param productCategoryId	产品分类id
	 * @param beginNo	起始行号
	 * @param pageSize	每页显示数量
	 * @return
	 */
	public List<Product> getProListByCondition(@Param("pwd")String pwd,
											   @Param("box")String box,
											   @Param("gasket")String gasket,
											   @Param("spongiaOne")String spongiaOne,
											   @Param("spongiaTwo")String spongiaTwo,
											   @Param("productCategoryId")int productCategoryId,
											   @Param("beginNo")int beginNo,
											   @Param("pageSize")int pageSize);
	
	/**
	 * 按条件查询包材信息数量
	 * @param pwd
	 * @param box
	 * @param gasket
	 * @param spongiaOne
	 * @param spongiaTwo
	 * @param productCategoryId
	 * @return
	 */
	public int countByCondition(@Param("pwd")String pwd,
			   @Param("box")String box,
			   @Param("gasket")String gasket,
			   @Param("spongiaOne")String spongiaOne,
			   @Param("spongiaTwo")String spongiaTwo,
			   @Param("productCategoryId")int productCategoryId);
	
	/**
	 * 按条件查询产品包材工艺
	 * @param pwd
	 * @return
	 */
	public Product getProListByPwd(@Param("pwd")String pwd);
	
	/**
	 * 增加产品包材工艺
	 * @param product
	 */
	public void addProduct(@Param("product") Product product);
	
	/**
	 * 按pwd查找是否存在产品包材工艺
	 * @param pwd
	 * @return
	 */
	public int getPro(@Param("pwd")String pwd);
	
	/**
	 * 按id删除产品包材工艺
	 * @param id
	 */
	public void delPro(@Param("id")int id);
	
	/**
	 * 根据id修改产品包材工艺
	 * @param id
	 */
	public void updatePro(@Param("product") Product product);
	
	/**
	 * 根据id查询产品包材工艺
	 * @param id
	 * @return
	 */
	public Product getProById(@Param("id")int id);
	
	/**
	 * 查询单个包材库存数量
	 * @param box
	 * @param gasket
	 * @param spongiaOne
	 * @param spongiaTwo
	 * @param esdBag
	 * @param geDang
	 * @param esdTable
	 * @param esdBubbleBag
	 * @param beginNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> getProductNum(@Param("box")String box,
									   @Param("gasket")String gasket,
									   @Param("spongiaOne")String spongiaOne,
									   @Param("spongiaTwo")String spongiaTwo,
									   @Param("esdBag")String esdBag,
									   @Param("geDang")String geDang,
									   @Param("esdTable")String esdTable,
									   @Param("esdBubbleBag")String esdBubbleBag,
									   @Param("beginNo")int beginNo,
									   @Param("pageSize")int pageSize);
	
	/**
	 * 根据产品分类ID查找产品
	 * @param id
	 * @return
	 */
	public List<Product> getProByProCate(@Param("productCategoryId")int productCategoryId);
	
	/**
	 * 查询所有产品分类数量
	 * @return
	 */
	public int countByProCategory();
	
	/**
	 * 查询所有产品分类
	 * @param beginNo
	 * @param pageSize
	 * @return
	 */
	public List<ProductCategory> getProCategoryList(@Param("beginNo")int beginNo,
													@Param("pageSize")int pageSize);
}

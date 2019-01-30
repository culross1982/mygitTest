package com.foxboro.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;

public interface ProductService {
	/**
	 * 查询所有包材信息
	 * @return
	 */
	//public List<Product> getProList(int beginNo,int pageSize);
	
	/**
	 * 所有包材信息数量
	 * @return
	 */
	//public int count();
	
	/**
	 * 按条件查询包材信息
	 * @param pwd
	 * @param box
	 * @param gasket
	 * @param spongiaOne
	 * @param spongiaTwo
	 * @param productCategoryId
	 * @param beginNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> getProListByCondition(String pwd,String box,String gasket,String spongiaOne,String spongiaTwo,
											   int productCategoryId,int beginNo,int pageSize);
	
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
	public int countByCondition(String pwd,String box,String gasket,String spongiaOne,String spongiaTwo,int productCategoryId);
	
	/**
	 * 按条件查询产品包材工艺
	 * @param pwd
	 * @return
	 */
	public Product getProListByPwd(String pwd);
	
	/**
	 * 增加产品包材工艺
	 * @param product
	 */
	public void addProduct(Product product);
	
	/**
	 * 从excel表中批量增加产品包材工艺
	 * @param product
	 */
	public void addProductFromXls(List<Product> product);
	
	/**
	 * 按pwd查找是否存在产品包材工艺
	 * @param pwd
	 * @return
	 */
	public int getPro(String pwd);
	
	/**
	 * 按id删除产品包材工艺
	 * @param id
	 */
	public void delPro(int id);
	
	/**
	 * 根据id修改产品包材工艺
	 * @param id
	 */
	public void updatePro(Product product);
	
	/**
	 * 根据id查询产品包材工艺
	 * @param id
	 * @return
	 */
	public Product getProById(int id);
	
	/**
	 * 根据产品分来ID查找产品
	 * @param id
	 * @return
	 */
	public List<Product> getProByProCate(int productCategoryId);
	
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
	public List<ProductCategory> getProCategoryList(int beginNo,int pageSize);
	
}

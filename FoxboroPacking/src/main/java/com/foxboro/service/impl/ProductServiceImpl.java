package com.foxboro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.ProductDao;
import com.foxboro.entity.Product;
import com.foxboro.entity.ProductCategory;
import com.foxboro.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	//按条件查询包材信息
	@Override
	public List<Product> getProListByCondition(String pwd, String box,
			String gasket, String spongiaOne, String spongiaTwo,
			int productCategoryId, int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return productDao.getProListByCondition(pwd, box, gasket, spongiaOne, spongiaTwo, productCategoryId, beginNo, pageSize);
	}
	
	//按条件查询包材信息数量
	@Override
	public int countByCondition(String pwd, String box, String gasket,
			String spongiaOne, String spongiaTwo, int productCategoryId) {
		// TODO Auto-generated method stub
		return productDao.countByCondition(pwd, box, gasket, spongiaOne, spongiaTwo, productCategoryId);
	}
	
	//按条件查询产品包材工艺
	@Override
	public Product getProListByPwd(String pwd) {
		// TODO Auto-generated method stub
		return productDao.getProListByPwd(pwd);
	}

	//增加产品包材工艺
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.addProduct(product);
	}
	
	//从excel表中批量增加产品包材工艺
	@Override
	public void addProductFromXls(List<Product> product) {
		// TODO Auto-generated method stub
		for (Product pro : product) {
			productDao.addProduct(pro);
		}
	}

	//按pwd查找是否存在产品包材工艺
	@Override
	public int getPro(String pwd) {
		// TODO Auto-generated method stub
		return productDao.getPro(pwd);
	}

	//按id删除产品包材工艺
	@Override
	public void delPro(int id) {
		// TODO Auto-generated method stub
		productDao.delPro(id);
	}

	//根据id修改产品包材工艺
	@Override
	public void updatePro(Product product) {
		// TODO Auto-generated method stub
		productDao.updatePro(product);
	}

	//根据id查询产品包材工艺
	@Override
	public Product getProById(int id) {
		// TODO Auto-generated method stub
		return productDao.getProById(id);
	}

	//根据产品分来ID查找产品
	@Override
	public List<Product> getProByProCate(int productCategoryId) {
		// TODO Auto-generated method stub
		return productDao.getProByProCate(productCategoryId);
	}

	
	//查询所有产品分类数量
	@Override
	public int countByProCategory() {
		// TODO Auto-generated method stub
		return productDao.countByProCategory();
	}
	
	//查询所有产品分类
	@Override
	public List<ProductCategory> getProCategoryList(int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return productDao.getProCategoryList(beginNo, pageSize);
	}

	

	

	

	

	

	

}

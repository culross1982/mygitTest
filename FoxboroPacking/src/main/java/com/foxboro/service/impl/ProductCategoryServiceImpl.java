package com.foxboro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxboro.dao.ProductCategoryDao;
import com.foxboro.dao.ProductDao;
import com.foxboro.entity.ProductCategory;
import com.foxboro.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	//产品分类列表
	@Override
	public List<ProductCategory> getCateList() {
		// TODO Auto-generated method stub
		return productCategoryDao.getCateList();
	}

	//新增产品分类
	@Override
	public int addCate(String productCategoryName) {
		// TODO Auto-generated method stub
		int result=0;
		boolean flag=true;
		if(productCategoryName!=null && !productCategoryName.equals("")){
			List<ProductCategory> ProCategories=productCategoryDao.getCateList();
			for (ProductCategory productCategory : ProCategories) {
				if(productCategory.getProductCategoryName().equals(productCategoryName)){
					result=-1;
					flag=false;
					break;
				}
			}
			if(flag==true){
				productCategoryDao.addCate(productCategoryName);
				result=1;
			}
		}
		return result;
	}

	//根据id修改产品分类
	@Override
	public void updataProCategory(int id) {
		// TODO Auto-generated method stub
		productCategoryDao.updataProCategory(id);
	}

	//根据id删除产品分类
	@Override
	public void delProCategory(int id) {
		// TODO Auto-generated method stub
		productCategoryDao.delProCategory(id);
	}
	
	

}

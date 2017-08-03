package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;




@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	
	///Field///
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao productDao;
	
	
	///Constructor///
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(":: "+getClass()+" default Constructor Call.....");
	}


	///Method///
	//setter method//
	public void setProductDao(ProductDao productDao) {
		System.out.println("::"+getClass()+".setUserDao Call.....");
		this.productDao = productDao;
	}

	//method//
	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.addProduct(product);
	}


	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		
		return productDao.getProduct(prodNo);
		
	}


	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}


	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);

	}

}

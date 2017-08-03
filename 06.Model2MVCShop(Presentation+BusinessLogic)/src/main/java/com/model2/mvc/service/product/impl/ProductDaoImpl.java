package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;



@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

	///Field///
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	
	///Constructor///
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(":: "+getClass()+" default constructor Call....");
	}

	
	///Method///
	//setter method//
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("ProductMapper.insertProduct", product);
	}


	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.findProduct", prodNo);		
	}


	@Override
	public List<Product> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}


	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("ProductMapper.updateProduct", product);
	}


	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}

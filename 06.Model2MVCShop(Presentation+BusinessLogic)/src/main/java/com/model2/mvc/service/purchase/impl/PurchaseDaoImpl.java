package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;


@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	///Field///
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	
	public void setSqlSession(SqlSession sqlSession){
		System.out.println("::"+getClass()+".setSqlSession() Call.....");
		this.sqlSession = sqlSession;
	}
	
	///Constructor///
	public PurchaseDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("::"+getClass()+" default Constructor Call.....");
	}

	
	///Method///
	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
	}

	@Override
	public Purchase findPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.findPurchase", tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search,String buyerId ) throws Exception{
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
			map.put("search", search);
			map.put("buyerId", buyerId);
		
			
			List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map); 
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
				list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.findProduct", list.get(i).getPurchaseProd().getProdNo()));
			}
			System.out.println(list);
			map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId));
	
			map.put("list", list);

		return map;
	}
	

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	@Override
	public int getTotalCount(String sql) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;


@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	///Field///
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	///Constructor///
	public PurchaseServiceImpl(){
		System.out.println(":: "+getClass()+" default Constructor Call.....");
	}
	
	
	///Method///
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		//System.out.println("PurchaseServiceImpl purchase :: "+purchase);
		purchaseDao.addPurchase(purchase);	
		
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.findPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		return purchaseDao.getPurchaseList(search, buyerId);
		
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updateTranCode(purchase);
	}

}

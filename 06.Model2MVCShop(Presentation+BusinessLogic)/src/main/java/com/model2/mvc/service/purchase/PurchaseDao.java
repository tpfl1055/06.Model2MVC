package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	// INSERT
	public int addPurchase(Purchase purchase) throws Exception;
	
	// SELECT ONE
	public Purchase findPurchase(int tranNo) throws Exception;
	
	// SELECT LIST
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	// SELECT LIST
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	// UPDATE
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// UPDATE
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(String sql) throws Exception;
	
}

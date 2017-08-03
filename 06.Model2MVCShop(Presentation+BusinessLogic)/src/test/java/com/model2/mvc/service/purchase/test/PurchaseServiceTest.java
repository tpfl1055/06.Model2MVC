package com.model2.mvc.service.purchase.test;




import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 * FileName : MyBatisTestApp14.java
  * :: Business Layer unit Test : Service + Persistence (Spring +mybatis + DAO)
  * ::  Annotation 기반 
  */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {
	
	
		@Autowired
		@Qualifier("purchaseServiceImpl")
		private PurchaseService purchaseService;
		
		@Autowired
		@Qualifier("purchaseDaoImpl")
		private PurchaseDao purchaseDao;
		
		
		@Test
		public void testAddPurchase() throws Exception{
				
			System.out.println("\n=====================================");
			
			Purchase purchase = new Purchase();
			Product purchaseProd = new Product();
			User buyer = new User();
			
			purchaseProd.setProdNo(10010);
			purchase.setPurchaseProd(purchaseProd);
			buyer.setUserId("user01");
			purchase.setBuyer(buyer);
			purchase.setReceiverName("쎌몬");
			purchase.setReceiverPhone("010-1234-1234");
			purchase.setDivyAddr("여기로");
			purchase.setDivyRequest("사무실로");
			purchase.setTranCode("1");
			
			purchaseService.addPurchase(purchase);
	
			//1. addPurchase Test  
			System.out.println("insert 결과 : "+ purchase ); 
		
			purchase = purchaseService.getPurchase(10010);
			
			Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
			Assert.assertEquals("user01", purchase.getBuyer().getUserId());
			Assert.assertEquals("쎌몬", purchase.getReceiverName());
			Assert.assertEquals("010-1234-1234", purchase.getReceiverPhone());
			Assert.assertEquals("여기로", purchase.getDivyAddr());
			Assert.assertEquals("사무실로", purchase.getDivyRequest());
			Assert.assertEquals("1", purchase.getTranCode());

			
			System.out.println("\n=====================================");
			
		}
		

		//@Test
		public void testgetPurchase() throws Exception{
		
			System.out.println("\n=====================================");

			//2. getProduct Test 
			Purchase purchase = purchaseService.getPurchase(10000);
			System.out.println("get 결과: "+purchase);
			
			Assert.assertEquals(10000, purchase.getTranNo());
			Assert.assertEquals(10008, purchase.getPurchaseProd().getProdNo());
			Assert.assertEquals("user01", purchase.getBuyer().getUserId());
			Assert.assertEquals("쎌몬", purchase.getReceiverName());
			Assert.assertEquals("010-1234-1234", purchase.getReceiverPhone());
			Assert.assertEquals("여기로", purchase.getDivyAddr());
			Assert.assertEquals("사무실로", purchase.getDivyRequest());
			Assert.assertEquals("1", purchase.getTranCode());
			
			System.out.println("\n=====================================");

		
		}
		
		
		//@Test
		public void testPurchaseList() throws Exception{
			
			System.out.println("\n=====================================");
			
			//3. getPurchaseList Test ::
	
			Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	//Assert.assertEquals(3, list.size());
			//==> console 확인
		 	for(int i=0 ; i<list.size() ; i++){
		 	System.out.println(list.get(i));
		 	}
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		
		System.out.println("\n=====================================");
		
		}
		
		
		//@Test
		public void testUpdatePurchase() throws Exception{
			
			System.out.println("\n=====================================");
			
			//4. uadateProduct Test  :: 
			Purchase purchase = purchaseService.getPurchase(10001);
			Assert.assertNotNull(purchase);
			Assert.assertEquals(10001, purchase.getTranNo());
			Assert.assertEquals(10008, purchase.getPurchaseProd().getProdNo());
			Assert.assertEquals("user01", purchase.getBuyer().getUserId());
			/*Assert.assertEquals("쎌몬", purchase.getReceiverName());
			Assert.assertEquals("010-1234-1234", purchase.getReceiverPhone());
			Assert.assertEquals("여기로", purchase.getDivyAddr());
			Assert.assertEquals("사무실로", purchase.getDivyRequest());
			Assert.assertEquals("1", purchase.getTranCode());*/
			
			purchase.setPaymentOption("2");
			purchase.setReceiverName("헬로");
			purchase.setReceiverPhone("777-7777-7777");
			purchase.setDivyAddr("거기로");
			purchase.setDivyRequest("빨리");
			
			
			
			purchaseService.updatePurchase(purchase);
			
			purchase = purchaseService.getPurchase(10001);
			Assert.assertNotNull(purchase);
			
			System.out.println("update 결과 : "+ purchase ); 
			
			Assert.assertEquals("2", purchase.getPaymentOption());
			Assert.assertEquals("헬로", purchase.getReceiverName());
			Assert.assertEquals("777-7777-7777", purchase.getReceiverPhone());
			Assert.assertEquals("거기로", purchase.getDivyAddr());
			Assert.assertEquals("빨리", purchase.getDivyRequest());
			
			System.out.println("\n=====================================");
			
		}
		
		
		//@Test
		public void testUpdateTranCode() throws Exception{
			
			System.out.println("\n=====================================");
			
			//5. uadateTranCode Test  :: 
			Purchase purchase = purchaseService.getPurchase(10001);
			Assert.assertNotNull(purchase);
			System.out.println(purchase);
			
			
			Assert.assertEquals(10001, purchase.getTranNo());
			Assert.assertEquals(10008, purchase.getPurchaseProd().getProdNo());
			Assert.assertEquals("user01", purchase.getBuyer().getUserId());
			/*Assert.assertEquals("하이", purchase.getReceiverName());
			Assert.assertEquals("010-1234-1234", purchase.getReceiverPhone());
			Assert.assertEquals("여기로", purchase.getDivyAddr());
			Assert.assertEquals("사무실로", purchase.getDivyRequest());*/
			Assert.assertEquals("2", purchase.getTranCode());
			
			
			Product purchaseProd = new Product();
			purchaseProd.setProdNo(10008);
			purchase.setPurchaseProd(purchaseProd);
			purchase.setTranCode("3");
			
			purchaseService.updateTranCode(purchase);
			
			purchase = purchaseService.getPurchase(10001);
			Assert.assertNotNull(purchase);
			
			System.out.println("update 결과 : "+ purchase ); 
			
			Assert.assertEquals("3", purchase.getTranCode());
			
			System.out.println("\n=====================================");
			
		}
		
		
		
		

}// end of class
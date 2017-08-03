package com.model2.mvc.service.product.test;




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
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

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
public class ProductServiceTest {
	
	
		@Autowired
		@Qualifier("productServiceImpl")
		private ProductService productService;
		
		@Autowired
		@Qualifier("productDaoImpl")
		private ProductDao productDao;
		
		
		//@Test
		public void testAddProduct() throws Exception{
				
			System.out.println("\n=====================================");
			
			//==> Test 용 Product instance 생성  
			Product product = new Product(); 
			product.setProdName("물건");
			product.setProdDetail("좋아요");
			product.setPrice(50000);
			
			
	
			//1. addProduct Test  
			System.out.println("insert 결과 : "+ productService.addProduct(product) ); 
			
			
			Assert.assertEquals(1, productService.addProduct(product));
			
			
			System.out.println("\n=====================================");
			//END::SqlSession  close
			/*System.out.println("::END::SqlSession 닫기..");
			sqlSession.close();*/
		}
		

		//@Test
		public void testgetProduct() throws Exception{
		
			System.out.println("\n=====================================");

			//2. getProduct Test :: 
			Product product = productService.getProduct(10000);
			System.out.println("get 결과: "+product);
			System.out.println(product.getProdNo());
			
			Assert.assertEquals(10000, product.getProdNo());
			Assert.assertNotNull(productService.getProduct(10000));
			
			System.out.println("\n=====================================");

		
		}
		
		
		//@Test
		public void testUpdateProduct() throws Exception{
			
			System.out.println("\n=====================================");
			
			//3. uadateProduct Test  :: 
			Product product = new Product(); //test14
			
			
			product.setProdNo(10008);
			product.setProdName("물건");
			product.setProdDetail("그럴거야");
			
			System.out.println("update 결과 : "+ productService.updateProduct(product) ); 
			
			Assert.assertEquals(1, productService.addProduct(product));
			
			System.out.println("\n=====================================");
			
		}
		
		@Test
		public void testProductList() throws Exception{
			
			System.out.println("\n=====================================");
			//4. getProductList Test ::
	
			Search search = new Search();
			
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = productService.getProductList(search);

		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
		 	list = (List<Object>)map.get("list");
		 	for(int i=0 ; i<list.size() ; i++){
			 	System.out.println(list.get(i));
			 	}
			
			Integer totalCount = (Integer)map.get("totalCount");
		 	
			System.out.println(totalCount);
			
			
		 	System.out.println("=======================================================");
		 	
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("");
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	for(int i=0 ; i<list.size() ; i++){
			 	System.out.println(list.get(i));
			 	}
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);

			System.out.println("\n=====================================");
		
		}
		
		
		

}// end of class
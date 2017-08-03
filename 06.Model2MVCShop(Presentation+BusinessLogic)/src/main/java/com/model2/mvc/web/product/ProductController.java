package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


@Controller
public class ProductController {

	///Field///
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	///Constructor///
	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass()+"Default Constructor Call");
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addProductView.do")
	public String addProductView(){
		
		System.out.println(">>> /addProductView.do start <<<");
		
		return "forward:/product/addProductView.jsp";
	}
	
	
	@RequestMapping("/addProduct.do")
	public String addProduct( @ModelAttribute("product") Product product) throws Exception{
		
		System.out.println(">>> /addProduct.do start <<<");
		System.out.println("addProduct product :: "+product);
		
		// Business Logic
		productService.addProduct(product);
		
		
		return "forward:/product/addProduct.jsp";
		
	}
	
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search, @RequestParam("menu") String menu, Model model) throws Exception{
		
		System.out.println(">>> /listProduct.do start <<<");
		System.out.println("listProduct search :: "+search);
		
		if(search.getCurrentPage() == 0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business Logic 수행
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp?menu="+menu;
	}
	
	
	@RequestMapping("/getProduct.do")
	public String getProduct( @RequestParam("prodNo") String prodNoStr, Model model) throws Exception{
		
		System.out.println(">>> /getProduct.do start <<<");
		
		// Business Logic 수행
		int prodNo = Integer.parseInt(prodNoStr);
		Product product = productService.getProduct(prodNo);
		
		// Model 과 View 연결
		model.addAttribute("product", product);

		return "forward:/product/getProduct.jsp";
		
	}
	
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView( @RequestParam("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println(">>> /updateProductView.do start <<<");
		
		// Business Logic 수행
		Product product = productService.getProduct(prodNo);
		
		// Model 과 View 연결
		model.addAttribute("product", product);

		return "forward:/product/updateProductView.jsp";
	}
	
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product ) throws Exception{
		
		System.out.println(">>> /updateProduct.do start <<<");
		
		// Business Logic 수행
		productService.updateProduct(product);
		
		System.out.println(product.getProdNo());
		
		return "forward:/getProduct.do?";//prodNo="+product.getProdNo();
	}
}

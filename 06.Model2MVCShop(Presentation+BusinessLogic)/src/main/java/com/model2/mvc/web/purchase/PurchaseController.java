package com.model2.mvc.web.purchase;

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
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {

	///Field///
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	///Constructor///
	public PurchaseController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass()+"Default Constructor Call");
	}

	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView( @RequestParam("prodNo") String prodNoStr) throws Exception{
		
		System.out.println(">>> /addPurchaseView.do start <<<");		
		
		int prodNo = Integer.parseInt(prodNoStr);	
		System.out.println("addPurchaseView prodNo :: "+prodNo);
		
		Product product = new Product();
		product = productService.getProduct(prodNo);
		System.out.println("addPurchaseView product :: "+product);

		//==> Model(data) / View(jsp)
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		modelAndView.addObject(product);
		
		return modelAndView;
		
	}
	
	
	/*@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase( @ModelAttribute("buyer") User buyer, @ModelAttribute("product") Product product) throws Exception{
		
		System.out.println(">>> /addPurchase.do start <<<");		
			
		System.out.println("addPurchase user :: "+buyer);
		System.out.println("addPurchase user :: "+product);
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(buyer);
		purchase.setPurchaseProd(product);
	
		// Business Logic 수행
		purchaseService.addPurchase(purchase);
		System.out.println("addPurchase purchase :: "+purchase);

		//==> Model(data) / View(jsp)
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		modelAndView.addObject(purchase);
		
		return modelAndView;
		
	}*/
	
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase( @ModelAttribute("purchase") Purchase purchase) throws Exception{
		
		System.out.println(">>> /addPurchase.do start <<<");		
			
		System.out.println("addPurchase purchase :: "+purchase);
	
		// Business Logic 수행
		purchaseService.addPurchase(purchase);
		System.out.println("addPurchase purchase :: "+purchase);

		//==> Model(data) / View(jsp)
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		modelAndView.addObject(purchase);
		
		return modelAndView;
		
	}
	
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase( @ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception{
		
		System.out.println(">>> /listPurchase.do start <<<");
		System.out.println("listPurchase search :: "+search);
		
		if(search.getCurrentPage() == 0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("user");
		
		String buyerId = user.getUserId();
		
		// Business Logic 수행
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);
		
		Page resultPage = new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}

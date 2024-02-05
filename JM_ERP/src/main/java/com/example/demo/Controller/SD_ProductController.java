package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.SD_PBproduct;
import com.example.demo.Entity.SD_Product;
import com.example.demo.Service.SD_productService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/SD/product")
@Controller
public class SD_ProductController {

	private final SD_productService productService; 
	
	

	
	@GetMapping("/regshop")
	public String searchAllproduct(Model model,@RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request ) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<SD_Product> paging = productService.searchAllproduct(page);
		model.addAttribute("paging",paging);
		
		return "SD_product_list";
	}
	
	@GetMapping("/pbproduct")
	public String searchPBproduct(Model model,@RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request ) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<SD_PBproduct> paging = productService.searchPbproduct(page);
		model.addAttribute("paging",paging);
		
		return "SD_pbProduct_list";
	}
	
	
	
	
//	
//	@GetMapping("/product/buy")
//	public String shopping(Model model,@RequestParam(value="page",defaultValue ="0") int page) {
//		Page<Product> paging = productService.search(page);
//		model.addAttribute("paging",paging);
//		return "product_list2";
//	}
//	
//	@GetMapping("/product/buy/{id}")
//	public String ProductDetail(@PathVariable("id") Long id,Model model) {
//		Optional<Product> p = productService.detail(id);
//		model.addAttribute("product", p);
//		
//		return "product_detail";
//	}
}

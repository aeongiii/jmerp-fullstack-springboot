package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Product;
import com.example.demo.Service.productService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/seller")
@Controller
public class ProductController {

	private final productService productService; 
	
	

	
	@GetMapping("/product")
	public String search(Model model,@RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request ) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<Product> paging = productService.search(page);
		model.addAttribute("paging",paging);
		
		return "product_list";
	}
	
	@GetMapping("/product/buy")
	public String shopping(Model model,@RequestParam(value="page",defaultValue ="0") int page) {
		Page<Product> paging = productService.search(page);
		model.addAttribute("paging",paging);
		return "product_list2";
	}
	
	@GetMapping("/product/buy/{id}")
	public String ProductDetail(@PathVariable("id") Long id,Model model) {
		Optional<Product> p = productService.detail(id);
		model.addAttribute("product", p);
		
		return "product_detail";
	}
}

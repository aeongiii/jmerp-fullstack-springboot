package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Product;
import com.example.demo.Service.productService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/seller")
@Controller
public class ProductController {

	private final productService productService; 
	
	@GetMapping("/product")
	public String search(Model model) {
		List<Product> list = productService.search();
		model.addAttribute("list",list);
		return "product_list";
	}
}

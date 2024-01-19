package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.NBProduct;
import com.example.demo.Service.NBproductService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/seller")
@Controller
public class NBproductcontroller {

	private final NBproductService nbproductService; 
	
	@GetMapping("/nbproduct")
	public String search(Model model) {
		List<NBProduct> list = nbproductService.search();
		model.addAttribute("list",list);
		return "NBproduct_list";
	}
}

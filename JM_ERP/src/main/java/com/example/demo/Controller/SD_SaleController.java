package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.SD_PurchaseService;
import com.example.demo.Service.SD_SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller	
@RequestMapping("/SD/sale")
public class SD_SaleController {
	
	@Autowired
	private final SD_PurchaseService purchaseService;
	private final SD_SaleService saleService;


// 인기상품 그래프 출력할 화면   
	@GetMapping("/best")
	public String bestGraph(Model model) {
		String graphHtmlPath = saleService.executePythonScript();
        model.addAttribute("graphHtmlPath", graphHtmlPath);
		return "SD/SD_best";
	}
}

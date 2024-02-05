package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_PurchaseSlip;
import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Service.AC_PurchaseSlipService;
import com.example.demo.Service.AC_SaleSlipService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AC_SlipController {
	//전표관리를 당담하는 웹사이트
	
	@Autowired
	private final AC_SaleSlipService saleSlipService;
	
	@Autowired
	private final AC_PurchaseSlipService purchaseSlipService;
	
	@GetMapping("/slip")
	public String List(Model model, @RequestParam(value = "page", defaultValue ="0") int page, HttpServletRequest request) {
        
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
    	Page<AC_SaleSlip> sale = this.saleSlipService.getList(page);
        Page<AC_PurchaseSlip> purchase = this.purchaseSlipService.getList(page);
        model.addAttribute("saleSlipList", sale);
        model.addAttribute("purchaseSlipList", purchase);
        return "AC_slip";
    }
}

package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_PurchaseSlip;
import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Entity.PD_BOM;
import com.example.demo.Service.AC_PurchaseSlipService;
import com.example.demo.Service.AC_SaleSlipService;
import com.example.demo.Service.PD_BOMService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final PD_BOMService bomservice;
	private final AC_SaleSlipService saleSlipService;
	private final AC_PurchaseSlipService purchaseSlipService;
//	private final SD_NBProductService nbProductService;
	
	@GetMapping("/")
    public String index(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
    	String currentUrl = request.getRequestURI();
    	model.addAttribute("currentUrl", currentUrl);
    	
    	Page<PD_BOM> bomlist = bomservice.getList(page);
    	Page<AC_SaleSlip> sale = this.saleSlipService.getList(page);
    	Page<AC_PurchaseSlip> purchase = this.purchaseSlipService.getList(page);
//    	Page<SD_NBProduct> paging = NBProductService.searchAllproduct(page);
    	
        model.addAttribute("bomlist", bomlist);
        model.addAttribute("saleSlipList", sale);
        model.addAttribute("purchaseSlipList", purchase);
//        model.addAttribute("regshop",paging);
    	return "index"; // resources/templates/index.html을 가리킵니다.
    }
}
	
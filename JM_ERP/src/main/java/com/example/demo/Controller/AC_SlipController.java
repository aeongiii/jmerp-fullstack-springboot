package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Entity.AC_WithdrawalSlip;
import com.example.demo.Service.AC_DepositSlipService;
import com.example.demo.Service.AC_SaleSlipService;
import com.example.demo.Service.AC_WithdrawalSlipService;
import com.example.demo.Service.SD_PurchaseService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/AC")
@RequiredArgsConstructor
@Controller
public class AC_SlipController {
	//전표관리를 당담하는 웹사이트
	
	private final AC_WithdrawalSlipService withdrawalSlipService;
	private final AC_DepositSlipService depositSlipService;
	private final AC_SaleSlipService saleSlipService;
	private final SD_PurchaseService purchaseService;
	
	@GetMapping("/transactionslip")
	public String transactionSlipList(Model model, 
			@RequestParam(value = "page", defaultValue ="0") int page,
    		@RequestParam(value = "keyword", required = false) String keyword, 
            @RequestParam(value = "category", required = false) String category,
            HttpServletRequest request) {
		
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
    	Page<AC_WithdrawalSlip> withdrawal = this.withdrawalSlipService.getList(page);
        Page<AC_DepositSlip> deposit = this.depositSlipService.getList(page);
        
	    if (category != null && !category.isEmpty()) {
	    	
	        if ("date".equals(category)) {
	            withdrawal = this.withdrawalSlipService.searchTradeDateList(keyword, page);
	            deposit = this.depositSlipService.searchTradeDateList(keyword, page);
	            
	        } else if ("trader".equals(category)) {
	        	withdrawal = this.withdrawalSlipService.searchTraderList(keyword, page);
	            deposit = this.depositSlipService.searchTraderList(keyword, page);
	        	
	        } else if ("description".equals(category)) {
	        	withdrawal = this.withdrawalSlipService.searchDescriptionList(keyword, page);
	            deposit = this.depositSlipService.searchDescriptionList(keyword, page);
	        
	        } else if ("transactionType".equals(category)) {
	        	withdrawal = this.withdrawalSlipService.searchTransactionTypeList(keyword, page);
	            deposit = this.depositSlipService.searchTransactionTypeList(keyword, page);	            
	        }
	    }
        
        
        model.addAttribute("withdrawalSlipList", withdrawal);
        model.addAttribute("depositSlipList", deposit);
        return "ac/AC_transactionslip";
    }
	
	@PostMapping("/transactionslip")
	public String transactionSlipUpdate(@RequestParam("transaction") String transaction) {
		
		if (transaction.equals("deposit")) {
		
		this.depositSlipService.update(this.saleSlipService.getList());
		}
		return "redirect:/AC/transactionslip";
	}
	
	@GetMapping("/saleslip")
	public String saleSlipList(Model model,     		
			@RequestParam(value = "page", defaultValue ="0") int page,
    		@RequestParam(value = "keyword", required = false) String keyword, 
            @RequestParam(value = "category", required = false) String category,
            HttpServletRequest request) {
		
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
        Page<AC_SaleSlip> sale = this.saleSlipService.getList(page);
        
	    if (category != null && !category.isEmpty()) {
	    	
	        if ("tradeDate".equals(category)) {
	            sale = this.saleSlipService.searchTradeDateList(keyword, page);
	            
	        } else if ("trader".equals(category)) {
	        	sale = this.saleSlipService.searchTraderList(keyword, page);
	        	
	        } else if ("seller".equals(category)) {
	        	sale = this.saleSlipService.searchSellerList(keyword, page);
	        	
	        } else if ("description".equals(category)) {
	        	sale = this.saleSlipService.searchDescriptionList(keyword, page);
	        
	        } else if ("transactionType".equals(category)) {
	        	sale = this.saleSlipService.searchTransactionTypeList(keyword, page);
	        }
	    }
        
        model.addAttribute("saleSlipList", sale);
        return "ac/AC_saleslip";
    }
	
	@PostMapping("/saleslip")
	public String saleSlipUpdate() {
		
		this.saleSlipService.update(this.purchaseService.getList());
		return "redirect:/AC/saleslip";
	}
}
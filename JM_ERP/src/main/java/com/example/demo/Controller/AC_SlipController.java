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
	public String transactionSlipList(Model model, @RequestParam(value = "page", defaultValue ="0") int page, HttpServletRequest request) {
        
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
    	Page<AC_WithdrawalSlip> withdrawal = this.withdrawalSlipService.getList(page);
        Page<AC_DepositSlip> deposit = this.depositSlipService.getList(page);
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
	public String saleSlipList(Model model, @RequestParam(value = "page", defaultValue ="0") int page, HttpServletRequest request) {
        
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
        Page<AC_SaleSlip> sale = this.saleSlipService.getList(page);
        model.addAttribute("saleSlipList", sale);
        return "ac/AC_saleslip";
    }
	
	@PostMapping("/saleslip")
	public String saleSlipUpdate() {
		
		this.saleSlipService.update(this.purchaseService.getList());
		return "redirect:/AC/saleslip";
	}
}
package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Entity.PC_OrderSheet;
import com.example.demo.Entity.PC_PurchaseInquiry;
import com.example.demo.Repository.MG_accountmgRepository;
import com.example.demo.Service.MG_acoountService;
import com.example.demo.Service.PC_PurchaseService;

import jakarta.servlet.http.HttpServletRequest;
@RequestMapping("/PC/purchase")
@Controller
public class PC_Purchasecontroller {
	
	
	@Autowired
	private PC_PurchaseService purchaseservice; 
	@Autowired
	private MG_acoountService accountMg;
	

    
	@GetMapping("/ordersheet")
	public String search(Model model, @RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PC_OrderSheet> paging = purchaseservice.searchOrderSheet(page);
		model.addAttribute("paging", paging);
		return "PC/PC_OrderSheet_list";
	}
	
	@GetMapping("/purchaseinquiry")
	public String searchPurchaseInquiry(Model model, @RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PC_PurchaseInquiry> paging = purchaseservice.searchPurchaseInquiry(page);
		model.addAttribute("paging", paging);
		return "PC/PC_PurchaseInquiry_list";
	}
	
	@GetMapping("/purchaseinquiry/create")
	public String purchaseCreateUrl(Model model) {
		List<Mg_AccountMG_Entity> account = accountMg.accountManager();
		
		model.addAttribute("account",account);
		
		return "PC/PC_purchaseinquiry_create";
	}
		
	@PostMapping("/purchaseinquiry/create")
	public String purchaseCreateform(
		@RequestParam(value="clientName") String clientName,
		@RequestParam(value="importedVoucher") String importedVoucher,
		@RequestParam(value="itemName") String itemName,
		@RequestParam(value="PurchaseDate") LocalDate PurchaseDate,
		@RequestParam(value="totalAmount") Double totalAmount,
		@RequestParam(value="warehouseName") String warehouseName)
		 {
	
		
		
		purchaseservice.purchaseSave( PurchaseDate,  clientName,  itemName , totalAmount,  warehouseName,importedVoucher);
		
		return "redirect:/PC/purchase/purchaseinquiry";
	}
}

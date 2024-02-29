package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Entity.PC_OrderSheet;
import com.example.demo.Entity.PC_PurchaseInquiry;
import com.example.demo.Entity.PD_Cost;
import com.example.demo.Service.MG_acoountService;
import com.example.demo.Service.PC_PurchaseService;
import com.example.demo.Service.PD_CostService;

import jakarta.servlet.http.HttpServletRequest;
@RequestMapping("/PC/purchase")
@Controller
public class PC_Purchasecontroller {
	
	double asd=123;
	Integer asdd =123;
	
	double a = asd*asdd;
	
	@Autowired
	private PC_PurchaseService purchaseservice; 
	@Autowired
	private MG_acoountService accountMg;
	@Autowired
	private PD_CostService costService;

    
	@GetMapping("/ordersheet")
	public String search(Model model, @RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PC_OrderSheet> paging = purchaseservice.searchOrderSheet(page);
		model.addAttribute("paging", paging);
		return "PC/PC_OrderSheet_list";
	}
	
	
	@GetMapping("/ordersheet/create/{id}")
	public String ordersheetCreateUrl(
			@PathVariable("id")Long id,
			
			
			Model model ,Model model1) {
		
		
		Optional<PC_PurchaseInquiry> ids = purchaseservice.findpurchase(id);
		Optional<PD_Cost> costList =  costService.getCost(ids.get().getItemName());
		
		model.addAttribute("ids",ids);
		model1.addAttribute("costList",costList);
		
		
		return "PC/PC_OrderSheetReg";
	}
	
	@PostMapping("/ordersheet/create/{id}")
	public String ordersheetCreateUrl(
			@PathVariable("id") Long num,
			@RequestParam("clinetName")String clinetName,
			@RequestParam("contactPerson") String contactPerson,
			@RequestParam("item")String item,
			@RequestParam("deliveryDate")LocalDate deliveryDate,
			@RequestParam("Count")Double Count,
			@RequestParam("totalPrice") Long totalPrice
		) {
		
	
		String bool = "예";
		String completionStatus = "N";
		
		purchaseservice.orderSheetSave(clinetName, contactPerson, item, deliveryDate, Count, completionStatus,totalPrice,bool,num);
		
		
		return "redirect:/PC/purchase/ordersheet";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------	
	
	
	@GetMapping("/purchaseinquiry")
	public String searchPurchaseInquiry(Model model, @RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PC_PurchaseInquiry> paging = purchaseservice.searchPurchaseInquiry(page);
		model.addAttribute("paging", paging);
		return "PC/PC_PurchaseInquiry_list";
	}
	
	@GetMapping("/purchaseinquiry/create")
	public String purchaseCreateUrl(Model model,Model model1) {
		List<Mg_AccountMG_Entity> account = accountMg.accountManager();
		List<PD_Cost> costList =  costService.getList();
		
		model.addAttribute("account",account);
		model1.addAttribute("costList",costList);
		return "PC/PC_purchaseinquiry_create";
	}
		
	@PostMapping("/purchaseinquiry/create")
	public String purchaseCreateform(
		@RequestParam(value="clientName") String clientName,
		@RequestParam(value="itemName") String itemName,
		@RequestParam(value="PurchaseDate") LocalDate PurchaseDate,
		@RequestParam(value="totalCount") Double totalCount,
		@RequestParam(value="warehouseName") String warehouseName)
		 {
	
		String acceptance = "아니요";
		
		purchaseservice.purchaseSave( PurchaseDate,  clientName,  itemName , totalCount,  warehouseName,acceptance);
		
		return "redirect:/PC/purchase/purchaseinquiry";
	}
}

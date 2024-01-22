package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Seller;
import com.example.demo.Service.SellerService;

@RequestMapping("/seller")
@Controller
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/list")
	public String search(Model model) {
		List<Seller> sellers = sellerService.searchAll();
		model.addAttribute("sellers", sellers);
		return "Seller_list";
	}
	
	@GetMapping("/Register")
	public String Reg() {
		return "RegSeller_form";
	}
	
	@PostMapping("/Register")
	public String RegSeller(@RequestParam("sellerId") String sellerId,
	                         @RequestParam("businessNumber") String businessNumber,
	                         @RequestParam("contactInfo") String contactInfo,
	                         @RequestParam("email") String email,
	                         @RequestParam("ceoName") String ceoName,
	                         @RequestParam("address") String address,
	                         @RequestParam("contract") boolean contract) {
	    
	    sellerService.RegSeller(sellerId, businessNumber, contactInfo, email, ceoName, address, contract);
	    
	    return "Complete_RegSeller";
	}

}

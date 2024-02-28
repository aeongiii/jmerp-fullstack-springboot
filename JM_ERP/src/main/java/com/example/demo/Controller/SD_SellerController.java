package com.example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.SD_sellerCreateForm;
import com.example.demo.Service.SD_SellerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/seller")
public class SD_SellerController {

	@Autowired
	private SD_SellerService sellerService;

	
// 셀러 등록
	@GetMapping("/create")
	public String sellerCreate(Model model) {
		model.addAttribute("SD_sellerCreateForm", new SD_sellerCreateForm()); // 셀러 등록 폼을 모델에 추가
		return "SD/SD_sellerCreate";
	}
	
	@PostMapping("/create")
	public String sellerCreate(@Valid SD_sellerCreateForm sellerCreateForm, Model model) {
		SD_SellerService.save(sellerCreateForm); // 모든 셀러 정보를 DB에 저장
		return "redirect:/SD/member/list";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/list")
//	public String search(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
//			HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//
//		Page<SD_Seller> paging = sellerService.searchAll(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Seller_list";
//	}
//
//	@GetMapping("/sellercommission")
//	public String searchCommission(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
//			HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//
//		Page<SD_SellerCommission> paging = sellerService.searchCommssion(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Commission_list";
//	}
//
//	@GetMapping("/commissionrate")
//	public String searchCommssionRate(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
//			HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//
//		Page<SD_CommissionRate> paging = sellerService.searchCommssionRate(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_CommissionRate_list";
//	}
//
//	@GetMapping("/sales")
//	public String searcha(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
//			HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//
//		Page<SD_Sales> paging = sellerService.searchSales(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Sales_list";
//	}
//
//	@GetMapping("/setcommissionrate")
//	public String setcommissionrate() {
//		return "SD_CommissionRate_Set";
//	}
//
//	@PostMapping("/setcommissionrate")
//	public String setcommissionrate1(@RequestParam(value = "category") String category,
//									 @RequestParam(value = "rate") Double rate) {
//		
//		sellerService.setCommssionRate(category, rate);
//		return "SD/SD_CommissionRate_Set";
//	}
//
//	@GetMapping("/Register")
//	public String Reg() {
//		return "SD/SD_sellerRegister";
//	}
//
//	@PostMapping("/Register")
//	public String RegSeller(@RequestParam("sellerId") String sellerId,
//			@RequestParam("businessNumber") String businessNumber, @RequestParam("contactInfo") String contactInfo,
//			@RequestParam("email") String email, @RequestParam("ceoName") String ceoName,
//			@RequestParam("address") String address) {
//
//		sellerService.RegSeller(sellerId, businessNumber, contactInfo, email, ceoName, address);
//
//		return "redirect:/SD/seller/list";
//	}

}

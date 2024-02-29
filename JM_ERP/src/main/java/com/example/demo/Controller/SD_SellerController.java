
package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_sellerCreateForm;
import com.example.demo.Service.SD_NBProductService;
import com.example.demo.Service.SD_SellerService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/seller")
public class SD_SellerController {

	private final SD_SellerService sellerService;
	private final SD_NBProductService nbService;

// 판매자 등록  
	@GetMapping("/create")
	public String sellerCreate(Model model) {
		model.addAttribute("SD_sellerCreateForm", new SD_sellerCreateForm()); // 셀러 등록 폼을 모델에 추가
		return "SD/SD_sellerCreate";
	}

	@PostMapping("/create")
	public String sellerCreate(@Valid SD_sellerCreateForm sellerCreateForm, Model model) {
		sellerService.saveSeller(sellerCreateForm); // 모든 셀러 정보를 DB에 저장
		return "redirect:/SD/member/list";
	}

// 전체 판매자 목록 조회
	@GetMapping("/list")
	public String sellerList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		String currentUrl = request.getRequestURI(); // 현재 요청한 URL을 모델에 저장
		model.addAttribute("currentUrl", currentUrl);
		
		Pageable pageable = PageRequest.of(page, 10); // 페이지 처리
		Page<SD_Seller> paging = sellerService.findAllExceptSpecificSellerId(pageable); // 자체회사 제외하는 메서드 호출 
		// paging = 요청한 page 번호에 해당하는 페이지의 사원 목록 + 페이지 수 + 페이지
																// 정보
		model.addAttribute("sellerList", paging.getContent());
		model.addAttribute("paging", paging);
		return "SD/SD_sellerList";
	}
	
// 판매자별 판매내역 조회 
	@GetMapping("/search")
	public String sellerSearch(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "sellerId", required = false) String sellerId,
			HttpServletRequest request) {
		
	    String currentUrl = request.getRequestURI();
	    model.addAttribute("currentUrl", currentUrl);

	    // 자체회사 제외하고, 모든 판매자 정보 조회하여 모델에 추가
	    List<SD_Seller> sellers = sellerService.findAllMembersExceptSelf();	// 자체회사 제외하는 메서드 호출
	    model.addAttribute("sellers", sellers);
	    
	    // 사업자등록번호에 따라 해당 판매자의 판매내역을 모델에 저장
	    Page<SD_NBProduct> paging;
	    if(sellerId != null && !sellerId.isEmpty()) {
	        paging = nbService.findBySellerId(sellerId, page); 
	    } else {
	        paging = nbService.searchAll(page);
	    }
	    
	    model.addAttribute("nbProductList", paging.getContent());
	    model.addAttribute("paging", paging);
	    return "SD/SD_sellerSearch";
		
	}



}

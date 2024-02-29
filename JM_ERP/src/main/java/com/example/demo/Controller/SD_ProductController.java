package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_NBProductCreateForm;
import com.example.demo.Service.SD_NBProductService;
import com.example.demo.Service.SD_PBProductService;
import com.example.demo.Service.SD_SellerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/product")
public class SD_ProductController {
	
	@Autowired
	private final SD_NBProductService nbService;
	private final SD_PBProductService pbService;
	private final SD_SellerService sellerService;
	
	
// 판매자 상품조회 @GetMapping("/nb")	
	
	
// 판매자 상품등록
	@GetMapping("/nbcreate")
	public String NBProductCreate(Model model) {
		List<SD_Seller> sellerList = sellerService.getSellerList();
		model.addAttribute("sellerList", sellerList);
		model.addAttribute("SD_NBProductCreateForm", new SD_NBProductCreateForm()); // nb상품 등록 폼을 모델에 추가
		return "SD/SD_NBProductCreate";
	}
	
	@PostMapping("/nbcreate")
	public String NBProductCreate(@Valid SD_NBProductCreateForm nbCreateForm, Model model) {
		nbService.saveNBProduct(nbCreateForm); // 상품코드를 form 및 DB에 저장
		return "redirect:/SD/member/list";	
	}
	
	
	
	
	// 판매자 상품수정
	
	// 판매자 상품삭제
	
	
}

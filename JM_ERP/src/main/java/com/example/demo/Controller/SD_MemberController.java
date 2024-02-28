package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Form.SD_memberCreateForm;
import com.example.demo.Service.SD_MemberService;
import com.example.demo.Service.SD_PurchaseService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/member")
public class SD_MemberController {
	
	@Autowired
	private final SD_MemberService memberService;
	private final SD_PurchaseService purchaseService;
	
	
// 고객 등록
	@GetMapping("/create")
	public String memberCreate(Model model) {
		model.addAttribute("SD_memberCreateForm", new SD_memberCreateForm()); // 고객 등록 폼을 모델에 추가
		return "SD/SD_memberCreate";
	}
	
	@PostMapping("/create")
	public String memberCreate(@Valid SD_memberCreateForm memCreateForm, Model model) {
		SD_MemberService.save(memCreateForm); // 모든 고객  정보를 DB에 저장
		return "redirect:/SD/member/list";	
	}
	
	
// 전체 고객 목록 조회
	@GetMapping("/list")
	public String memberList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		String currentUrl = request.getRequestURI();	// 현재 요청한 URL을 모델에 저장
		model.addAttribute("currentUrl", currentUrl);

		Page<SD_Member> paging = memberService.searchAll(page);	// paging = 요청한 page 번호에 해당하는 페이지의 사원 목록 + 페이지 수 + 페이지 정보
		model.addAttribute("memList", paging.getContent());	
		model.addAttribute("paging", paging);
		return "SD/SD_memberList";
	}
	
	
// 고객 구매내역 조회
	@GetMapping("/search")
	public String memberSearch(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "memberId", required = false) String memberId,
			HttpServletRequest request) {
		
	    String currentUrl = request.getRequestURI();
	    model.addAttribute("currentUrl", currentUrl);

	    // 모든 고객 정보 조회하여 모델에 추가
	    List<SD_Member> members = memberService.findAllMembers();
	    model.addAttribute("members", members);
	    
	    // 고객 id에 따라 해당 고객의 구매내역을 모델에 저장
	    Page<SD_Purchase> paging;
	    if(memberId != null && !memberId.isEmpty()) {
	        paging = purchaseService.findByMemberId(memberId, page); 
	    } else {
	        paging = purchaseService.searchAll(page);
	    }
	    
	    model.addAttribute("purchaseList", paging.getContent());
	    model.addAttribute("paging", paging);
	    return "SD/SD_memberSearch";
		
	}
	

	
}
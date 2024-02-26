package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/member")
public class SD_MemberController {
	
//	@Autowired
//	private final SD_MemberService memberService;
//	
//	@GetMapping("/register")
//	public String RegMember() {
//		return "Regmember";
//	}
//	
//	
//	
//	@PostMapping("/register")
//	public String RegMember(@RequestParam("memberId") String memberId,
//			@RequestParam("name") String name,
//			@RequestParam("dateOfBirth")  LocalDate dateOfBirth,
//			@RequestParam("country") String country,
//			@RequestParam("contactNumber") String contactNumber,
//			@RequestParam("email") String email,
//			@RequestParam("address") String address,
//			@RequestParam("gender") String gender,
//			@RequestParam("membership") boolean membership
//
//			) { 
//	memberService.RegiMember(memberId, name, dateOfBirth, country, contactNumber, email, address, gender, membership);
//		return "Complete_RegMember";
//	}
//	
//	
//	
//	@GetMapping("/list")
//	public String searchMember(Model model, @RequestParam(value = "page",defaultValue ="0") int page,HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//		
//		Page<SD_Member> paging = memberService.searchMember(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Member_list";
//	}
//	
//	
//	
//	@PostMapping("/list")
//	public String searchMemberbyname(Model model, @RequestParam(name = "name") String name,@RequestParam(value = "page",defaultValue ="0")int page) {
//		Page<SD_Member> paging = memberService.searchMemberbyname(page,name);
//		System.out.println(name);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Member_list";
//	}
//	
//	
//	
//	@GetMapping("/purchase")
//	public String searchPurchase(Model model, @RequestParam(value = "page",defaultValue ="0") int page,HttpServletRequest request) {
//		String currentUrl = request.getRequestURI();
//		model.addAttribute("currentUrl", currentUrl);
//		
//		Page<SD_Purchase> paging = memberService.searchPurchase(page);
//		model.addAttribute("paging", paging);
//		return "SD/SD_Purchase_list";
//	}
	
	

	
}

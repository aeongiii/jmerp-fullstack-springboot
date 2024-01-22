package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Member;
import com.example.demo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private final MemberService memberService;
	
	@GetMapping("/register")
	public String RegMember() {
		return "Regmember";
	}
	
	@PostMapping("/register")
	public String RegMember(@RequestParam("memberId") String memberId,
			@RequestParam("name") String name,
			@RequestParam("dateOfBirth")  LocalDate dateOfBirth,
			@RequestParam("country") String country,
			@RequestParam("contactNumber") String contactNumber,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("gender") String gender,
			@RequestParam("membership") boolean membership

			) { 
	
	memberService.RegMember(memberId, name, dateOfBirth, country, contactNumber, email, address, gender, membership);
		
		
		return "Complete_RegMember";
	}
	
	@GetMapping("/list")
	public String searchMember(Model model) {
		List<Member> Members =memberService.searchMember();
		model.addAttribute("members", Members);
		return "Member_list";
	}
}

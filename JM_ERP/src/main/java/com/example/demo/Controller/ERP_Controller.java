package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.ERP_user;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Service.ERP_UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ERP_Controller {

	@Autowired
	private final ERP_UserService erp_UserService;

	@GetMapping("/signup")
	public String signupForm(Model model) {
		ERP_user user = new ERP_user();
		user.setHR_mem(new HR_mem());
		model.addAttribute("user", user);
		
		return "ERP_signup_form";
	}

	@PostMapping("/signup")
	public String ERP_signup(@RequestParam("userId") String userId, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("employeeId") String employeeId) {
		
		boolean exists = erp_UserService.create(userId, password, name, employeeId);
		
		if (exists) {
			return "Complete_signup";
		} else {
			return "Fail_signup";

		}
	}

}

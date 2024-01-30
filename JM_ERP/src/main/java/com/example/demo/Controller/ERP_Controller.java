package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.ERP_UserService;
import com.example.demo.security.ERP_signUpForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class ERP_Controller {

	@Autowired
	private final ERP_UserService erp_UserService;

	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("signupForm", new ERP_signUpForm());

		return "ERP_signup_form";
	}

	// BindingResult bindingResult 사용 가능성 검토
	@PostMapping("/signup")
	public String ERP_signup(@ModelAttribute ERP_signUpForm signupForm, Model model) {

		try {
			boolean exists = erp_UserService.create(signupForm.getUserId(), signupForm.getPassword(),
					signupForm.getName(), signupForm.getEmployeeId());

			if (exists) {
				return "redirect:/user/login";

			} else {
				return "ERP_Fail_signup";

			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("signupForm", new ERP_signUpForm());
			return "ERP_signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("signupForm", new ERP_signUpForm());
			return "ERP_signup_form";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "ERP_login";
	}
}

package com.example.demo.Controller;


import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.ERP_userMailBox;
import com.example.demo.Form.ERP_sendMailForm;
import com.example.demo.Service.ERP_UserService;
import com.example.demo.security.ERP_signUpForm;

import jakarta.servlet.http.HttpServletRequest;
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
			boolean exists = erp_UserService.createuser(signupForm.getUserId(), signupForm.getPassword(),
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

	@GetMapping("/mail")
		public String openmailbox(Model model,Principal principal,@RequestParam(value="page",defaultValue ="0") int page,HttpServletRequest request) {
		
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<ERP_userMailBox> paging = erp_UserService.openmailbox(principal.getName(),page);
		
		model.addAttribute("paging", paging);
		return "ERP_mailbox";
	}
//	@PostMapping("/mail")
//	public String maildetail(@RequestParam(name = "num") Long num) {
//		System.out.println(num);
//	return "ERP_mailopen";
//	}
	
	@GetMapping("/mailsend")
	public String sendmail(Model model){
		model.addAttribute("sendMailForm", new ERP_sendMailForm());
		return "ERP_mailsend";
	}

	@PostMapping("/mailsend")
	public String sendmailP(@ModelAttribute ERP_sendMailForm sendMailForm,Model model,Principal principal) {
		boolean exists = erp_UserService.sendnewmail(sendMailForm.getReciveUser(), principal.getName(), sendMailForm.getSubject(), 
				sendMailForm.getContent(), sendMailForm.getImage(), sendMailForm.getMediaFile());
		
		if(!exists) {
			return "redirect:/user/mail";
		}
		return "redirect:/user/mail";
	}

	@GetMapping("/maildetail{num}")
	public String maildetail(Model model,@PathVariable("num") Long num) {
		Optional<ERP_userMailBox> mail = erp_UserService.findbynum(num);
		erp_UserService.checkmailstatus(num);
		
		ERP_userMailBox a = mail.get();
		model.addAttribute("mail", a);
		return "ERP_mailopen";
	}
}

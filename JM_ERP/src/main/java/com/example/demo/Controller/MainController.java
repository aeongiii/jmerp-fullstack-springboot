package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.ERP_approval;
import com.example.demo.Entity.ERP_boardQ;
import com.example.demo.Service.ERP_UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final ERP_UserService userservice;
	
	@GetMapping("/")
    public String index(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
    	String currentUrl = request.getRequestURI();
    	model.addAttribute("currentUrl", currentUrl);
    	
    	Page<ERP_approval> approvalList = this.userservice.approvalList(page);
    	Page<ERP_boardQ> questionList = this.userservice.QuestionGetList(page);
		
    	model.addAttribute("approvalList", approvalList);  
		model.addAttribute("questionlist", questionList); 
    	      
    	return "index"; // resources/templates/index.html을 가리킵니다.
    }
}
	
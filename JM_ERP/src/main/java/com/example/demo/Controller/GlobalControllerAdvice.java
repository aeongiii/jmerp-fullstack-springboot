package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public void globalAttributes(Model model, Principal principal) {
        try {
    	if (principal != null) {
            model.addAttribute("login_username", principal.getName());
        } 
    	
        }catch(Exception e) {
            model.addAttribute("login_username", "Guest");
        }
    }

    

}




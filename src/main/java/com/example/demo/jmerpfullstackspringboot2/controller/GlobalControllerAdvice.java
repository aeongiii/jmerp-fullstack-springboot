package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.ERP_user;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_mem;
import com.example.demo.jmerpfullstackspringboot2.service.ERP_UserService;
import com.example.demo.jmerpfullstackspringboot2.service.HR_memService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

    private final ERP_UserService userservice;
    private final HR_memService memservice;

    @ModelAttribute
    public void globalAttributes(Model model, Principal principal) {
        try {
            if (principal != null) {
                Optional<ERP_user> user = userservice.getName(principal.getName());
                HR_mem mem = memservice.getMem(user.get().getName());
                if (user.isPresent()) {
                    model.addAttribute("login_username", mem.getName() + '(' + mem.getDeptName() + ')');

                } else {
                    model.addAttribute("login_username", principal.getName());

                }
            }

        } catch (Exception e) {
            model.addAttribute("login_username", "Guest");
        }
    }


}




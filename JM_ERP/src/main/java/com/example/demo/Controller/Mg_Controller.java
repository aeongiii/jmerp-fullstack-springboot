package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Service.MG_acoountService;

import lombok.RequiredArgsConstructor;



@RequestMapping("/mgcon")
@RequiredArgsConstructor
@Controller
public class Mg_Controller {

	@Autowired
	private final MG_acoountService mgser;
	
	
@GetMapping("/accmg")
public String mgcheck(Model model, @RequestParam(value="page", defaultValue="0") int page) {
	Page<Mg_AccountMG_Entity> paging = this.mgser.getList(page);
	model.addAttribute("paging", paging);
	
	return "mgacccheck";
}

}

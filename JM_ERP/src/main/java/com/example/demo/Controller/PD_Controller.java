package com.example.demo.Controller;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Form.PD_BOMCreateForm;
import com.example.demo.Service.PD_BOMService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/PD")
public class PD_Controller {
	
	private final PD_BOMService bomservice;
	
	@GetMapping("/bom")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_BOM> paging = bomservice.getList(page);
		model.addAttribute("paging", paging);
		return "PD_bom";
	}
	

	@GetMapping("/bom/regi")
	public String regi(PD_BOMCreateForm bomcreateform) {
		return "PD_BOMregi";
	}
	
	@PostMapping("/bom/regi")
	public String regi(@Valid PD_BOMCreateForm bomcreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "PD_BOMregi";
		}
		
		bomservice.create(bomcreateform.getProdCode(), bomcreateform.getProdName(), 
				bomcreateform.getRawMatNum(), bomcreateform.getUnit(), bomcreateform.getNum(), 
				bomcreateform.getType(), bomcreateform.getRawNum(), bomcreateform.getWorkTime());
		
		
		return "redirect:/PD/bom";
	}
}

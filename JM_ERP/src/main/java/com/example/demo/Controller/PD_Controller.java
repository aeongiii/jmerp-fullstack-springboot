package com.example.demo.Controller;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Entity.PD_WO;
import com.example.demo.Form.PD_BOMCreateForm;
import com.example.demo.Form.PD_WOCreateForm;
import com.example.demo.Service.PD_BOMService;
import com.example.demo.Service.PD_WOService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/PD")
public class PD_Controller {
	
	private final PD_BOMService bomservice;
	private final PD_WOService woservice;
	
	// --------------------- PD_BOM -------------------------------//
	
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
	
	// -------------------- PD_WO ----------------------------------//
	
	@GetMapping("/wo")
	public String WO_list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_WO> paging = woservice.getList(page);
		model.addAttribute("paging",paging);
		return "PD_WO";
	}
	
	@GetMapping("/wo/regi")
	public String WO_regi(PD_WOCreateForm wocreateform) {
		return "PD_WOregi";
	}
	
	@PostMapping("/wo/regi")
	public String WO_regi(@Valid PD_WOCreateForm wocreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "PD_WOregi";
		}
		
		woservice.create(wocreateform.getOrderNum(), wocreateform.getDeliveryName(), wocreateform.getManager(),
				wocreateform.getDeliveryDate(), wocreateform.getProdCode(), wocreateform.getWOrder() ,
				wocreateform.getMaking(), wocreateform.getFactory());
		return "redirect:/PD/wo";
	}
	
	// -------------------- PD_WorkHistory ----------------------------------//
	
	
	// -------------------- PD_RS ----------------------------------//
	
	// -------------------- PD_QC ----------------------------------//
	
	// -------------------- PD_Cost ----------------------------------//
}


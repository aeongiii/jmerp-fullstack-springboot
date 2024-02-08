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
import com.example.demo.Entity.PD_Cost;
import com.example.demo.Entity.PD_QC;
import com.example.demo.Entity.PD_RS;
import com.example.demo.Entity.PD_WO;
import com.example.demo.Entity.PD_WorkHistory;
import com.example.demo.Form.PD_BOMCreateForm;
import com.example.demo.Form.PD_CostCreateForm;
import com.example.demo.Form.PD_QCCreateForm;
import com.example.demo.Form.PD_WOCreateForm;
import com.example.demo.Service.PD_BOMService;
import com.example.demo.Service.PD_CostService;
import com.example.demo.Service.PD_QCService;
import com.example.demo.Service.PD_RSService;
import com.example.demo.Service.PD_WOService;
import com.example.demo.Service.PD_WorkHistoryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/PD")
public class PD_Controller {
	
	private final PD_BOMService bomservice;
	private final PD_WOService woservice;
	private final PD_WorkHistoryService whservice;
	private final PD_RSService rsservice;
	private final PD_QCService qcservice;
	private final PD_CostService costservice;
	// --------------------- PD_BOM -------------------------------//
	@GetMapping("/bom")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_BOM> paging = bomservice.getList(page);
		model.addAttribute("paging", paging);
		return "pd/PD_bom";
	}

	@GetMapping("/bom/regi")
	public String regi(PD_BOMCreateForm bomcreateform) {
		return "pd/PD_BOMregi";
	}
	
	@PostMapping("/bom/regi")
	public String regi(@Valid PD_BOMCreateForm bomcreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pd/PD_BOMregi";
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
		return "pd/PD_WO";
	}
	
	@GetMapping("/wo/regi")
	public String WO_regi(PD_WOCreateForm wocreateform) {
		return "pd/PD_WOregi";
	}
	
	@PostMapping("/wo/regi")
	public String WO_regi(@Valid PD_WOCreateForm wocreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pd/PD_WOregi";
		}
		
		woservice.create(wocreateform.getOrderNum(), wocreateform.getDeliveryName(), wocreateform.getManager(),
				wocreateform.getDeliveryDate(), wocreateform.getProdCode(), wocreateform.getWOrder() ,
				wocreateform.getMaking(), wocreateform.getFactory());
		return "redirect:/PD/wo";
	}
	
	// -------------------- PD_WorkHistory ----------------------------------//
	
	@GetMapping("/wh")
	public String WH_list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_WorkHistory> paging = whservice.getList(page);
		model.addAttribute("paging",paging);
		return "pd/PD_WorkHistory";
	}
	
	// -------------------- PD_RS ----------------------------------//
	
	@GetMapping("/rs")
	public String RS_list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_RS> paging = rsservice.getList(page);
		model.addAttribute("paging",paging);
		return "pd/PD_RS";
	}
	
	// -------------------- PD_QC ----------------------------------//
	
	@GetMapping("/qc")
	public String QC_list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_QC> paging = qcservice.getList(page);
		model.addAttribute("paging",paging);
		return "pd/PD_QC";
	}
	
	@GetMapping("/qc/regi")
	public String QC_regi(PD_QCCreateForm qccreateform) {
		return "pd/PD_QCregi";
	}
	
	@PostMapping("/qc/regi")
	public String QC_regi(@Valid PD_QCCreateForm qccreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pd/PD_QCregi";
		}
		
		qcservice.create(qccreateform.getId(), qccreateform.getQcTool(), qccreateform.
				getProdCode(), qccreateform.getProdNum(), qccreateform.getQcNum(), 
				qccreateform.getQcList(), qccreateform.getPF());
		return "redirect:/PD/qc";
	}
	// -------------------- PD_Cost ----------------------------------//
	
	@GetMapping("/cost")
	public String Cost_list(Model model, @RequestParam(value="page", defaultValue="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<PD_Cost> paging = costservice.getList(page);
		model.addAttribute("paging",paging);
		return "pd/PD_Cost";
	}
	
	@GetMapping("/cost/regi")
	public String Cost_regi(PD_CostCreateForm costcreateform) {
		return "pd/PD_Costregi";
	}
	
	@PostMapping("/cost/regi")
	public String Cost_regi(@Valid PD_CostCreateForm costcreateform, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pd/PD_Costregi";
		}
		
		costservice.create(costcreateform.getPd_bom(), costcreateform.getProdName(), 
				costcreateform.getKg(), costcreateform.getCost());
		return "redirect:/PD/cost";
	}
}


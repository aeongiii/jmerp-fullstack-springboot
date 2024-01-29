package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Service.BOMService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PD_Controller {
	
	private final BOMService bomservice;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<PD_BOM> bomlist = this.bomservice.getList();
		model.addAttribute("bomlist", bomlist);
		return "bom_list";
	}
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping(value = "/list/bom/{ProdCode}")
	public String bom(Model model, @PathVariable("ProdCode") String prodCode) {
		List<PD_BOM> item = this.bomservice.getItem(prodCode);
		model.addAttribute("list_bom",item);
		return "list_bom";
	}
}

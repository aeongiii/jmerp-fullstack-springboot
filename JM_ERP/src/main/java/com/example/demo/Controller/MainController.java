package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.BOM;
import com.example.demo.Service.BOMService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final BOMService bomservice;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BOM> bomlist = this.bomservice.getList();
		model.addAttribute("bomlist", bomlist);
		return "bom_list";
	}
}

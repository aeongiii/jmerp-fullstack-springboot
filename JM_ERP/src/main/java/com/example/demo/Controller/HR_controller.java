package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Service.HR_memService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/HR")
@RequiredArgsConstructor
@Controller
public class HR_controller {
	
	private final HR_memService memService;

	
	@GetMapping("")
	public String HR() {
		return "HR_main";
	}
	
	@GetMapping("/mem/list")
	public String list(Model model) {
		List<HR_mem> memList = this.memService.getList();
		model.addAttribute("memList", memList);
		return "HR_memList";
	}
}


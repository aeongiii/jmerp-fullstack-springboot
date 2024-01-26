package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HR_controller {
	
	private final HR_memRepository memRepository;

	@GetMapping("/HR")
	public String list(Model model) {
		List<HR_mem> memList = this.memRepository.findAll();
		model.addAttribute("memList", memList);
		return "HR_memList";
	}
}

// 20240126 merge 직후 파일을 내 브런치에 다시 push.
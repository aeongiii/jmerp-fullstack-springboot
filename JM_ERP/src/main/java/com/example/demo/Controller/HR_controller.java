package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Service.HR_memService;

import jakarta.validation.Valid;
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
	
	@GetMapping("/mem/create")
	public String create(Model model) {
		List<HR_dept> deptList = memService.getdeptList();
		model.addAttribute("deptList", deptList);
		model.addAttribute("HR_memCreateForm", new HR_memCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR_memCreate";
	}
	
	@PostMapping("/mem/create")
	public String create(@Valid HR_memCreateForm memCreateForm, Model model) {
		String employeeId = memService.createEmployeeId(memCreateForm.getDeptName(), memCreateForm.getStartDate());
		memCreateForm.setEmployeeId(employeeId); // 사원 번호를 폼 객체에 설정
	    memService.save(memCreateForm); // 사원 정보 저장
		return "redirect:/HR/mem/list";
	}
	

}


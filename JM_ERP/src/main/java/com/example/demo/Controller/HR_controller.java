package com.example.demo.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.HR_deptCreateForm;
import com.example.demo.Form.HR_deptUpdateForm;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Service.HR_deptService;
import com.example.demo.Service.HR_memService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/HR")
@RequiredArgsConstructor
@Controller
public class HR_controller {

	private final HR_memService memService;
	private final HR_deptService deptService;
// ========================================= main =============================================
	@GetMapping("")
	public String HR() {
		return "HR_main";
	}

// ========================================= 1. 사원 =============================================

	@GetMapping("/mem/create")
	public String create(Model model) {
		List<HR_dept> deptList = deptService.getdeptList();
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

	@GetMapping("/mem/search") // 매개변수가 선택적임
	public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		if (keyword != null && !keyword.isEmpty()) {
			List<HR_mem> searchResults = memService.search(keyword);
			model.addAttribute("searchResults", searchResults);
		}
		return "HR_memSearch";
	}
	
	@GetMapping("/mem/list")
	public String list(Model model, @RequestParam(value="page",defaultValue ="0") int page, HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
		Page<HR_mem> paging = memService.searchAll(page);
		model.addAttribute("memList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR_memList";
	}
	
	
// ========================================= 2. 부서 =============================================

	@GetMapping("/dept/create")
	public String create2(Model model) {
		List<HR_dept> deptList = deptService.getdeptList();
		model.addAttribute("deptList", deptList);
		model.addAttribute("HR_deptCreateForm", new HR_deptCreateForm()); // HR_deptCreateForm 객체를 모델에 추가
		return "HR_deptCreate";
	}
	
	@PostMapping("/dept/create")
	public String create2(@Valid HR_deptCreateForm deptCreateForm, BindingResult result, Model model) {
		if(result.hasErrors()) {	// BindingResult : @Valid 에서 유효성 검증 오류 발생 시 다시 화면 출력
			return "HR_deptCreate";
		}
		
		deptService.save2(deptCreateForm.getDeptName());
		return "redirect:/HR";
	}
	
	//부서 수정 -> 오류 발생.
//	@GetMapping("/dept/update")
//	public String update2(@RequestParam("deptCode") String deptCode, Model model) {
//		HR_dept dept = deptService.findByDeptCode(deptCode);
//		if(dept !=null) {
//		HR_deptUpdateForm deptUpdateForm = new HR_deptUpdateForm();
//		deptUpdateForm.setDeptCode(dept.getDeptCode());
//		deptUpdateForm.setDeptName(dept.getDeptName());
//		
//		model.addAttribute("HR_deptUpdateForm", deptUpdateForm);
//		}
//		return "HR_deptUpdate";
//	}
	
	
//	@PostMapping("/dept/update")
//	public String update2(@Valid HR_deptUpdateForm deptUpdateForm, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "HR_deptUpdate";
//		}
//		
//		deptService.update2(deptUpdateForm.getDeptCode(), deptUpdateForm.getDeptName());
//		return "redirect:/HR";
//	}
	
	// 부서 삭제 -> 오류 발생.
//	@GetMapping("/dept/delete/{deptCode}")
//	public String delete2(@RequestParam String deptCode) {
//		deptService.delete2(deptCode);
//		return "redirect:/HR";
//	}

}

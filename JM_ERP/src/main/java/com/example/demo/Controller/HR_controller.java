package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.HR_cont;
import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;
import com.example.demo.Form.HR_contCreateForm;
import com.example.demo.Form.HR_contUpdateForm;
import com.example.demo.Form.HR_dayCreateForm;
import com.example.demo.Form.HR_deptCreateForm;
import com.example.demo.Form.HR_deptUpdateForm;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Form.HR_vacationCreateForm;
import com.example.demo.Form.HR_workCreateForm;
import com.example.demo.Service.HR_contService;
import com.example.demo.Service.HR_dayService;
import com.example.demo.Service.HR_deptService;
import com.example.demo.Service.HR_memService;
import com.example.demo.Service.HR_workService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;



@RequestMapping("/HR")
@RequiredArgsConstructor
@Controller
public class HR_controller {

	private final HR_memService memService;
	private final HR_deptService deptService;
	private final HR_workService workService;
	private final HR_contService contService;
	private final HR_dayService dayService;

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
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
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
		if (result.hasErrors()) { // BindingResult : @Valid 에서 유효성 검증 오류 발생 시 다시 화면 출력
			return "HR_deptCreate";
		}

		deptService.save2(deptCreateForm.getDeptName());
		return "redirect:/HR/dept/create";
	}

//부서 수정 -> 오류 발생.
	@GetMapping("/dept/update")
	public String update2(@RequestParam("deptName") String olddeptName, Model model) {
		HR_dept dept = deptService.findByDeptName(olddeptName);
		if (dept != null) {
			HR_deptUpdateForm deptUpdateForm = new HR_deptUpdateForm();
			deptUpdateForm.setOldDeptName(dept.getDeptName());

			model.addAttribute("HR_deptUpdateForm", deptUpdateForm);
		}
		return "HR_deptUpdate";
	}

	@PostMapping("/dept/update")
	public String update2(@Valid HR_deptUpdateForm deptUpdateForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HR_deptUpdate";
		}

		deptService.updateDeptName(deptUpdateForm.getOldDeptName(), deptUpdateForm.getNewDeptName());
		return "redirect:/HR/dept/create";
	}

// 부서 삭제
	@PostMapping("/dept/delete/{deptName}")
	public String delete2(@PathVariable("deptName") String deptName) {
		deptService.delete2(deptName);
		return "redirect:/HR/dept/create";
	}

// ========================================= 3. 근태관리 =============================================

// 출퇴근 등록	
	@GetMapping("/work/create")
	public String createWork(Model model) {
		List<HR_work> workList = workService.getworkList();
		model.addAttribute("workList", workList);
		model.addAttribute("HR_workCreateForm", new HR_workCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR_workCreate";
	}

	@PostMapping("/work/create")
	public String createWork(@Valid HR_workCreateForm workCreateForm, Model model) {

		workService.saveWork(workCreateForm); // 사원 정보 저장
		return "redirect:/HR/mem/list";
	}

// 휴가 등록
	@GetMapping("/work/vacation")
	public String createVacation(Model model) {
		List<HR_work> workList = workService.getworkList();
		model.addAttribute("workList", workList);
		model.addAttribute("HR_vacationCreateForm", new HR_vacationCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR_vacationCreate";
	}

	@PostMapping("/work/vacation")
	public String createvacation(@Valid HR_vacationCreateForm vacationCreateForm, Model model) {

		workService.saveVacation(vacationCreateForm); // 사원 정보 저장
		return "redirect:/HR/mem/list";
	}

// 근태내역 조회 (사원별 / 월별)
	@GetMapping("/work/search")
	public String listWork(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);

		Page<HR_work> paging = workService.searchAll(page);
		model.addAttribute("workList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR_workSearch";
	}

// ========================================= 4. 초과근무 =============================================

// ========================================= 5. 근로계약서 =============================================

// 근로계약서 등록	
	@GetMapping("/cont/create")
	public String createCont(Model model) {
		List<HR_cont> contList = contService.getcontList();
		model.addAttribute("contList", contList);
		model.addAttribute("HR_contCreateForm", new HR_contCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR_contCreate";
	}

	@PostMapping("/cont/create")
	public String createCont(@Valid HR_contCreateForm contCreateForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "HR_contCreate"; // 오류가 있는 경우, 폼 페이지로 다시 이동
		}
		contService.saveCont(contCreateForm); // 사원 정보 저장
		return "redirect:/HR/cont/list";
	}

// 근로계약서 전체 리스트 출력		
	@GetMapping("/cont/list") // 근로계약서 리스트 출력
	public String listCont(Model model) {
		List<HR_cont> contList = contService.getcontList();
		model.addAttribute("contList", contList);
		return "HR_contList";
	}

// 근로계약서 수정
	@GetMapping("/cont/update/{id}")
	public String updateCont(@PathVariable("id") int id, Model model) {
		HR_cont cont = contService.getContById(id);
		HR_contCreateForm form = new HR_contCreateForm();

		form.setId(cont.getId()); // 엔티티의 id -> 폼 객체에 같이 전달
		
		// 이미 존재하는 HR_cont 근로계약서 객체를 HR_contCreateForm에 매핑 -> 기존 데이터가 폼에 보이도록
		form.setEmployeeId(cont.getEmployeeId().getEmployeeId()); // .getEmployeeId()를 두번 사용 : HR_mem 호출 후 실제 사원번호 호출
		form.setContractName(cont.getContractName());
		form.setContractDate(cont.getContractDate());
		form.setSignA(cont.isSignA() ? "요청완료" : "미요청"); // SignA의 속성값이 true 이면 웹에서 "요청완료"를, 속성값이 false 이면 웹에서 "미요청"으로
														// 선택됨
		form.setSignB(cont.isSignB() ? "서명완료" : "미완료");

		model.addAttribute("HR_contUpdateForm", form);

		return "HR_contUpdate"; 
	}

	@PostMapping("/cont/update/{id}")
	public String updateCont(@PathVariable("id") int id, @Valid HR_contUpdateForm contUpdateForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사에 실패한 경우, 동일한 폼으로 돌아가 오류 메시지 표시
			return "HR_contUpdate";
		}
		contService.updateCont(contUpdateForm.getId(), contUpdateForm);
		return "redirect:/HR/cont/list";
	}

// 근로계약서 삭제
		@PostMapping("/cont/delete/{id}")
		public String deleteCont(@PathVariable("id") int id) {
			contService.deleteCont(id);
			return "redirect:/HR/cont/list";
		}
		
		
// ========================================= 6. 일용근무 =============================================
		
// 일용근무사원 등록	
		@GetMapping("/day/create")
		public String createDay(Model model) {
			List<HR_dept> deptList = deptService.getdeptList();
			model.addAttribute("deptList", deptList);
			model.addAttribute("HR_dayCreateForm", new HR_dayCreateForm()); // HR_memCreateForm 객체를 모델에 추가
			return "HR_dayCreate";
		}

		@PostMapping("/day/create")
		public String createDay(@Valid HR_dayCreateForm dayCreateForm, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) {
				return "HR_dayCreate"; // 오류가 있는 경우, 폼 페이지로 다시 이동
			}
			dayService.saveDay(dayCreateForm); // 사원 정보 저장
			return "redirect:/HR/mem/list";
		}
		
// 일용근무사원 조회 메서드부터 만들어야 !!!!!!!!!
}

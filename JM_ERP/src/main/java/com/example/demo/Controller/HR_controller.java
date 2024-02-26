package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.HR_cont;
import com.example.demo.Entity.HR_day;
import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_doc;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;
import com.example.demo.Form.HR_contCreateForm;
import com.example.demo.Form.HR_contUpdateForm;
import com.example.demo.Form.HR_dayCreateForm;
import com.example.demo.Form.HR_dayUpdateForm;
import com.example.demo.Form.HR_deptCreateForm;
import com.example.demo.Form.HR_deptUpdateForm;
import com.example.demo.Form.HR_docCreateForm;
import com.example.demo.Form.HR_docUpdateForm;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Form.HR_vacationCreateForm;
import com.example.demo.Form.HR_workCreateForm;
import com.example.demo.Form.HR_workUpdateForm;
import com.example.demo.Service.HR_contService;
import com.example.demo.Service.HR_dayService;
import com.example.demo.Service.HR_deptService;
import com.example.demo.Service.HR_docService;
import com.example.demo.Service.HR_memService;
import com.example.demo.Service.HR_workService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

// feature/update2/kye - 코드 검토 및 정리

@RequestMapping("/HR")
@RequiredArgsConstructor
@Controller
public class HR_controller {

	private final HR_memService memService;
	private final HR_deptService deptService;
	private final HR_workService workService;
	private final HR_contService contService;
	private final HR_dayService dayService;
	private final HR_docService docService;

// ========================================= main =============================================
	@GetMapping("")
	public String HR() {
		return "HR/HR_main";
	}

// ========================================= 1. 사원 =============================================

// 사원 등록
// 사원 등록
	@GetMapping("/mem/create")
	public String create(Model model) {
		model.addAttribute("deptList", deptList);	// 전체 사원 목록을 가져와 'deptList'라는 이름으로 모델에 추가
		model.addAttribute("HR_memCreateForm", new HR_memCreateForm()); // 사원 등록 폼을 모델에 추가
		model.addAttribute("HR_memCreateForm", new HR_memCreateForm()); // 사원 등록 폼을 모델에 추가
		return "HR/HR_memCreate";
	}

	@PostMapping("/mem/create")
	public String create(@Valid HR_memCreateForm memCreateForm, Model model) {
		memCreateForm.setEmployeeId(employeeId); // 만들어진 사원 번호를 폼 객체의 employeeId에 설정
		memService.save(memCreateForm); // 모든 사원 정보를 DB에 저장
		memService.save(memCreateForm); // 모든 사원 정보를 DB에 저장
		return "redirect:/HR/mem/list";	
	}
// 사원 검색

// 사원 검색
	@GetMapping("/mem/search") // 매개변수가 선택적임
			List<HR_mem> searchResults = memService.search(keyword);	// 키워드가 null이 아닐 경우 검색
		if (keyword != null && !keyword.isEmpty()) {
			List<HR_mem> searchResults = memService.search(keyword);	// 키워드가 null이 아닐 경우 검색
			model.addAttribute("searchResults", searchResults);
		}
		return "HR/HR_memSearch";
// 전체 사원 검색
	}

// 전체 사원 검색
		String currentUrl = request.getRequestURI();	// 현재 요청한 URL을 모델에 저장
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		Page<HR_mem> paging = memService.searchAll(page);	// paging = 요청한 page 번호에 해당하는 페이지의 사원 목록 + 페이지 수 + 페이지 정보
		model.addAttribute("currentUrl", currentUrl);

		Page<HR_mem> paging = memService.searchAll(page);	// paging = 요청한 page 번호에 해당하는 페이지의 사원 목록 + 페이지 수 + 페이지 정보
		model.addAttribute("memList", paging.getContent());	
		model.addAttribute("paging", paging);
		return "HR/HR_memList";
	}
// 부서 등록

// ========================================= 2. 부서 =============================================

// 부서 등록
		model.addAttribute("HR_deptCreateForm", new HR_deptCreateForm()); // 부서 등록 폼을 모델에 추가
	public String create2(Model model) {
		List<HR_dept> deptList = deptService.getdeptList();
		model.addAttribute("deptList", deptList);
		model.addAttribute("HR_deptCreateForm", new HR_deptCreateForm()); // 부서 등록 폼을 모델에 추가
						  // @valid 어노테이션 : 폼 데이터가 바인딩될때 유효성 검사(@NotEmpty, @Email 등 이미 선언된 제약조건을 검증)
						  // @valid가 붙은 파라미터 "뒤에" BindingResult가 오면 --> 검증 오류 발생 시 BindingResult 객체에 저장함
		return "HR/HR_deptCreate";
		if (result.hasErrors()) { // 유효성 검증 오류 발생 시 다시 화면 출력

	@PostMapping("/dept/create")
		deptService.save2(deptCreateForm.getDeptName());	// 새로운 부서코드 자동 생성 후 DB에 저장
	public String create2(@Valid HR_deptCreateForm deptCreateForm, BindingResult result, Model model) {
		if (result.hasErrors()) { // 유효성 검증 오류 발생 시 다시 화면 출력
			return "HR/HR_deptCreate";
//부서 수정
		deptService.save2(deptCreateForm.getDeptName());	// 새로운 부서코드 자동 생성 후 DB에 저장
		return "redirect:/HR/dept/create";
	}

//부서 수정
	@GetMapping("/dept/update")
	public String update2(@RequestParam("deptName") String olddeptName, Model model) {
		HR_dept dept = deptService.findByDeptName(olddeptName);
		if (dept != null) {
			HR_deptUpdateForm deptUpdateForm = new HR_deptUpdateForm();
			deptUpdateForm.setOldDeptName(dept.getDeptName());

			model.addAttribute("HR_deptUpdateForm", deptUpdateForm);
		}
		return "HR/HR_deptUpdate";
	}

	@PostMapping("/dept/update")
	public String update2(@Valid HR_deptUpdateForm deptUpdateForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "HR/HR_deptUpdate";
		}

		deptService.updateDeptName(deptUpdateForm.getOldDeptName(), deptUpdateForm.getNewDeptName());
		return "redirect:/HR/dept/create";
		deptService.delete2(deptName);	// ID값(=deptName)으로 해당 데이터 찾아서 삭제

// 부서 삭제
	@PostMapping("/dept/delete/{deptName}")
	public String delete2(@PathVariable("deptName") String deptName) {
		deptService.delete2(deptName);	// ID값(=deptName)으로 해당 데이터 찾아서 삭제
		return "redirect:/HR/dept/create";
	}

// ========================================= 3. 근태관리 =============================================
		model.addAttribute("workList", workList);	// 전체 근태 데이터 모델에 추가
		model.addAttribute("HR_workCreateForm", new HR_workCreateForm()); // 폼 객체를 모델에 추가
	@GetMapping("/work/create")
	public String createWork(Model model) {
		List<HR_work> workList = workService.getworkList();
		model.addAttribute("workList", workList);	// 전체 근태 데이터 모델에 추가
		model.addAttribute("HR_workCreateForm", new HR_workCreateForm()); // 폼 객체를 모델에 추가
	}

	@PostMapping("/work/create")
	public String createWork(@Valid HR_workCreateForm workCreateForm, Model model) {
		workService.saveWork(workCreateForm); // 사원 정보 저장
		return "redirect:/HR/work/search";
	}

// 휴가 등록
	@GetMapping("/work/vacation")
	public String createVacation(Model model) {
		List<HR_work> workList = workService.getworkList();
		model.addAttribute("workList", workList);
		model.addAttribute("HR_vacationCreateForm", new HR_vacationCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR/HR_vacationCreate";
	}

	@PostMapping("/work/vacation")
	public String createvacation(@Valid HR_vacationCreateForm vacationCreateForm, Model model) {

		workService.saveVacation(vacationCreateForm); // 사원 정보 저장
		return "redirect:/HR/work/search";
	
						  // @RequestParam = HTTP 요청에서 특정값을 가져와 메서드의 파라미터로 바인딩 ㄱㄴ ----> value = 요청 파라미터의 이름 / defaultValue = null이거나 빈 문자열일 경우 기본값
						  // 																	   required = 요청 파라미터의 필수 여부. (기본값 true)
	}

// 근태내역 조회 (사원별 / 월별)
	@GetMapping("/work/search")
	
			HttpServletRequest request) {	// HttpServletRequest = HTTP 요청에 대한 모든 정보를 저장 --> 메서드 파라미터로 사용 시 다양한 HTTP 정보에 접근 가능
		
						  // 																	   required = 요청 파라미터의 필수 여부. (기본값 true)
	public String listWork(Model model,  @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "type", required = false, defaultValue = "monthly") String type,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "employeeId", required = false) String employeeId,
			HttpServletRequest request) {	// HttpServletRequest = HTTP 요청에 대한 모든 정보를 저장 --> 메서드 파라미터로 사용 시 다양한 HTTP 정보에 접근 가능
			// type = 'monthly'이고, year와 month가 null이 아닐 경우 ---> 월별 조회 로직
		
		model.addAttribute("currentUrl", currentUrl);
	    }
	    	// type = 'employee'이고, employeeId가 null 또는 빈 문자열이 아닐 경우 ---> 사원별 조회 로직
	    else if ("employee".equals(type) && employeeId != null && !employeeId.isEmpty()) {
		Page<HR_work> paging = workService.searchAll(page);
	    } 
			// type = 'monthly'이고, year와 month가 null이 아닐 경우 ---> 월별 조회 로직
	    else {
	    if ("monthly".equals(type) && year != null && month != null) { 
	        paging = workService.searchByMonth(year, month, PageRequest.of(page, 10));
	    
	    // 현재 선택된 조회 조건을 모델에 추가
	    model.addAttribute("selectedType", type); 
	    	// type = 'employee'이고, employeeId가 null 또는 빈 문자열이 아닐 경우 ---> 사원별 조회 로직
	    else if ("employee".equals(type) && employeeId != null && !employeeId.isEmpty()) {
	        paging = workService.searchByEmployee(employeeId, PageRequest.of(page, 10));
	    } 
	    	// 기본 조회 로직 (모든 근태 내역)
	    else {
	        paging = workService.searchAll(PageRequest.of(page, 10));
	    }
	    
	    // 현재 선택된 조회 조건을 모델에 추가
	    model.addAttribute("selectedType", type); 
	    
	    List<HR_mem> employees = memService.getList(); 
        model.addAttribute("employees", employees);	// 사원 목록을 모델에 추가
        
		model.addAttribute("workList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR/HR_workSearch";
	}

// 근태내역 수정
	@GetMapping("/work/update/{id}")
	public String updateWork(@PathVariable("id") int id, Model model) {
		HR_work work = workService.getWorkById(id);
		HR_workCreateForm form = new HR_workCreateForm();

		// 이미 존재하는 HR_doc 객체를 HR_docCreateForm에 매핑 -> 기존 데이터가 폼에 보이도록
		form.setEmployeeId(work.getEmployeeId().getEmployeeId()); // 날짜는 기존 데이터 추가하기 실패ㅠㅠ
		form.setEndTime(work.getEndTime());
		form.setName(work.getName());
		form.setOvertimeHour(work.getOvertimeHour());
		form.setOvertimePay(work.getOvertimePay());
		form.setOvertimeType(work.getOvertimeType());
		form.setStartTime(work.getStartTime());
		form.setWorkHour(work.getWorkHour());
		form.setId(work.getId());
		model.addAttribute("HR_workUpdateForm", form);
		return "HR/HR_workUpdate";
	}

	@PostMapping("/work/update/{id}")
	public String updateWork(@PathVariable("id") int id, @Valid HR_workUpdateForm workUpdateForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사에 실패한 경우, 동일한 폼으로 돌아가 오류 메시지 표시
			return "HR/HR_workUpdate";
		}
		
		// 서비스의 updateWork에서 EntityNotFoundException 오류 발생할 경우, 사용자에게 다시입력하도록 메시지 추가
		try {
	        workService.updateWork(id, workUpdateForm);
	    } catch (EntityNotFoundException e) {
	        model.addAttribute("errorMessage", "사원이 존재하지 않습니다. 다시 입력하세요.");
	        return "HR/HR_workUpdate"; // 동일한 수정 페이지로 돌아가기
	    }
		
		workService.updateWork(id, workUpdateForm);
		return "redirect:/HR/work/search";
	}

// 근태내역 삭제
	@PostMapping("/work/delete/{id}")
	public String deleteWork(@PathVariable("id") int id) {
		workService.deleteWork(id);
		return "redirect:/HR/work/search";
	}

// ========================================= 4. 근로계약서 =============================================

// 근로계약서 등록	
	@GetMapping("/cont/create")
	public String createCont(Model model) {
		List<HR_cont> contList = contService.getcontList();
		model.addAttribute("contList", contList);
		model.addAttribute("HR_contCreateForm", new HR_contCreateForm()); // HR_contCreateForm 객체를 모델에 추가
		return "HR/HR_contCreate";
	}

	@PostMapping("/cont/create")
	public String createCont(@Valid HR_contCreateForm contCreateForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "HR/HR_contCreate"; // 오류가 있는 경우, 폼 페이지로 다시 이동
		}
		contService.saveCont(contCreateForm); // 사원 정보 저장
		return "redirect:/HR/cont/list";
	}

// 근로계약서 전체 리스트 출력		
	@GetMapping("/cont/list") // 근로계약서 리스트 출력
	public String listCont(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		List<HR_cont> contList = contService.getcontList();
		model.addAttribute("contList", contList);

		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);

		Page<HR_cont> paging = contService.searchAll(page);
		model.addAttribute("memList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR/HR_contList";
	}

	// 근로계약서 수정
	@GetMapping("/cont/update/{id}")
	public String updateCont(@PathVariable("id") int id, Model model) {
		HR_cont cont = contService.getContById(id);
		HR_contCreateForm form = new HR_contCreateForm();

		form.setId(cont.getId()); // 엔티티의 id -> 폼 객체에 같이 전달

		// 이미 존재하는 HR_cont 객체를 HR_contCreateForm에 매핑 -> 기존 데이터가 폼에 보이도록
		form.setContractDate(cont.getContractDate()); // 날짜는 기존 데이터 추가하기 실패ㅠㅠ
		form.setContractName(cont.getContractName());
		form.setEmployeeId(cont.getEmployeeId().getEmployeeId());
		form.setName(cont.getName());
		form.setSignA(cont.isSignA() ? "요청완료" : "미요청"); // SignA의 속성값이 true 이면 웹에서 "요청완료"를, 속성값이 false 이면 웹에서 "미요청"으로 선택
		form.setSignB(cont.isSignB() ? "서명완료" : "미완료");

		model.addAttribute("HR_contUpdateForm", form);
		return "HR/HR_contUpdate";
	}

	@PostMapping("/cont/update/{id}")
	public String updatecont(@PathVariable("id") int id, @Valid HR_contUpdateForm contUpdateForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사에 실패한 경우, 동일한 폼으로 돌아가 오류 메시지 표시
			return "HR/HR_contUpdate";
		}
		contService.updateCont(id, contUpdateForm);
		return "redirect:/HR/cont/list";
	}

// 근로계약서 삭제
	@PostMapping("/cont/delete/{id}")
	public String deleteCont(@PathVariable("id") int id) {
		contService.deleteCont(id);
		return "redirect:/HR/cont/list";
	}

// ========================================= 5. 일용근무 =============================================

// 일용근무사원 등록	
	@GetMapping("/day/create")
	public String createDay(Model model) {
		List<HR_dept> deptList = deptService.getdeptList();
		model.addAttribute("deptList", deptList);
		model.addAttribute("HR_dayCreateForm", new HR_dayCreateForm()); // HR_memCreateForm 객체를 모델에 추가
		return "HR/HR_dayCreate";
	}

	@PostMapping("/day/create")
	public String createDay(@Valid HR_dayCreateForm dayCreateForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "HR/HR_dayCreate"; // 오류가 있는 경우, 폼 페이지로 다시 이동
		}
		dayService.saveDay(dayCreateForm); // 사원 정보 저장
		return "redirect:/HR/day/list";
	}

// 일용근무사원 조회
	@GetMapping("/day/list") // 리스트 출력
	public String listDay(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		List<HR_day> dayList = dayService.getDayList();
		model.addAttribute("dayList", dayList);

		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);

		Page<HR_mem> paging = memService.searchAll(page);
		model.addAttribute("memList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR/HR_dayList";
	}

// 일용근무사원 수정
	@GetMapping("/day/update/{id}")
	public String updateDay(@PathVariable("id") int id, Model model) {
		HR_day day = dayService.getDayById(id);
		HR_dayCreateForm form = new HR_dayCreateForm();

		form.setId(day.getId()); // 엔티티의 id -> 폼 객체에 같이 전달

		// 이미 존재하는 HR_day 객체를 HR_dayCreateForm에 매핑 -> 기존 데이터가 폼에 보이도록
		form.setDayWorkDate(day.getDayWorkDate()); // 날짜는 기존 데이터 추가하기 실패ㅠㅠ
		form.setDayWorkHour(day.getDayWorkHour());
		form.setDayWorkName(day.getDayWorkName());
		form.setDayWorkPay(day.getDayWorkPay());
		form.setDeptName(day.getDeptName());

		// 기존 선택된 부서명도 수정 폼에 추가
		List<HR_dept> deptList = deptService.getdeptList();
		model.addAttribute("deptList", deptList); // 부서목록도 폼에 필요하니까 같이 추가

		model.addAttribute("HR_dayUpdateForm", form);
		return "HR/HR_dayUpdate";
	}

	@PostMapping("/day/update/{id}")
	public String updateDay(@PathVariable("id") int id, @Valid HR_dayUpdateForm dayUpdateForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사에 실패한 경우, 동일한 폼으로 돌아가 오류 메시지 표시
			return "HR/HR_dayUpdate";
		}
		dayService.updateDay(id, dayUpdateForm);
		return "redirect:/HR/day/list";
	}

// 일용근무사원 삭제
	@PostMapping("/day/delete/{id}")
	public String deleteDay(@PathVariable("id") int id) {
		dayService.deleteCont(id);
		return "redirect:/HR/day/list";
	}

// ========================================= 6. 증명서 =============================================

// 증명서 등록
	@GetMapping("/doc/create")
	public String createDoc(Model model) {
		List<HR_doc> docList = docService.getDocList();
		model.addAttribute("docList", docList);
		model.addAttribute("HR_docCreateForm", new HR_docCreateForm()); // HR_docCreateForm 객체를 모델에 추가
		return "HR/HR_docCreate";
	}

	@PostMapping("/doc/create")
	public String createDoc(@Valid HR_docCreateForm docCreateForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "HR/HR_docCreate"; // 오류가 있는 경우, 폼 페이지로 다시 이동
		}
		docService.saveDoc(docCreateForm); // 사원 정보 저장
		return "redirect:/HR/doc/list";
	}

// 증명서 전체 리스트 출력
	@GetMapping("/doc/list") // 근로계약서 리스트 출력
	public String listDoc(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			HttpServletRequest request) {
		List<HR_doc> docList = docService.getDocList();
		model.addAttribute("docList", docList);

		String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);

		Page<HR_mem> paging = memService.searchAll(page);
		model.addAttribute("memList", paging.getContent());
		model.addAttribute("paging", paging);
		return "HR/HR_docList";
	}

// 증명서 수정
	@GetMapping("/doc/update/{docNum}")
	public String updateDoc(@PathVariable("docNum") String docNum, Model model) {
		HR_doc doc = docService.getDocById(docNum);
		HR_docCreateForm form = new HR_docCreateForm();

		// 이미 존재하는 HR_doc 객체를 HR_docCreateForm에 매핑 -> 기존 데이터가 폼에 보이도록
		form.setName(doc.getName()); // 날짜는 기존 데이터 추가하기 실패ㅠㅠ
		form.setDocNum(doc.getDocNum());
		form.setDocType(doc.getDocType());
		form.setDocUse(doc.getDocUse());
		form.setDocDate(doc.getDocDate());
		form.setEmployeeId(doc.getEmployeeId().getEmployeeId());

		model.addAttribute("HR_docUpdateForm", form);
		return "HR/HR_docUpdate";
	}

	@PostMapping("/doc/update/{docNum}")
	public String updateDoc(@PathVariable("docNum") String docNum, @Valid HR_docUpdateForm docUpdateForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사에 실패한 경우, 동일한 폼으로 돌아가 오류 메시지 표시
			return "HR/HR_docUpdate";
		}
		docService.updateDoc(docNum, docUpdateForm);
		return "redirect:/HR/doc/list";
	}

// 증명서 삭제
	@PostMapping("/doc/delete/{docNum}")
	public String deleteDoc(@PathVariable("docNum") String docNum) {
		docService.deleteDoc(docNum);
		return "redirect:/HR/doc/list";
	}
}

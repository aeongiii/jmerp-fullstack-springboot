package com.example.demo.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;
import com.example.demo.Form.HR_vacationCreateForm;
import com.example.demo.Form.HR_workCreateForm;
import com.example.demo.Form.HR_workUpdateForm;
import com.example.demo.Repository.HR_memRepository;
import com.example.demo.Repository.HR_workRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_workService {

	private final HR_memRepository memRepository;
	private final HR_workRepository workRepository;

	public List<HR_work> getworkList() {
		// TODO Auto-generated method stub
		return null;
	}

// 출퇴근 등록
	public void saveWork(@Valid HR_workCreateForm workCreateForm) {
		// HR_mem 엔티티 조회
		HR_mem employee = memRepository.findById(workCreateForm.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));

		// 현재 날짜
		LocalDate today = LocalDate.now();

		// HR_work 엔티티 찾기 또는 생성
		Optional<HR_work> optionalWork = workRepository.findByEmployeeIdAndWorkDate(employee, today);
		HR_work work;
		if (optionalWork.isPresent()) {
			work = optionalWork.get();
		} else {
			work = new HR_work();
			work.setEmployeeId(employee);
			work.setWorkDate(today);
		}

		// HR_work 엔티티 설정
		work.setName(employee.getName());
		work.setStartTime(workCreateForm.getStartTime());
		work.setEndTime(workCreateForm.getEndTime());
		work.setOvertimeType(workCreateForm.getOvertimeType());
		// 나머지 필드는 기본값이나 null 상태로 유지

		// workHour 계산
		long hoursBetween = ChronoUnit.HOURS.between(workCreateForm.getStartTime(), workCreateForm.getEndTime());
	    work.setWorkHour((int) hoursBetween);
		
	    // overtimeHour 및 overtimePay 설정
	    if (workCreateForm.getOvertimeType() != null && !workCreateForm.getOvertimeType().isEmpty()) {
	        work.setOvertimeHour(workCreateForm.getOvertimeHour());
	        work.setOvertimePay(workCreateForm.getOvertimePay());
	    }
	    
		// HR_work 저장
		workRepository.save(work);
	}

// 휴가 등록
	public void saveVacation(@Valid HR_vacationCreateForm vacationCreateForm) {
		// HR_mem 엔티티 조회
		HR_mem employee = memRepository.findById(vacationCreateForm.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));

		// 현재 날짜
		LocalDate today = LocalDate.now();

		// HR_work 엔티티 찾기 또는 생성
		Optional<HR_work> optionalWork = workRepository.findByEmployeeIdAndWorkDate(employee, today);
		HR_work work;
		if (optionalWork.isPresent()) {
			work = optionalWork.get();
		} else {
			work = new HR_work();
			work.setEmployeeId(employee);
			work.setWorkDate(today);
		}

		// HR_work 엔티티 설정
		work.setName(employee.getName());
		work.setAttendance(vacationCreateForm.getAttendance());
		// 나머지 필드는 기본값이나 null 상태로 유지

		// HR_work 저장
		workRepository.save(work);
	}
	
// 근태내역 조회
		public List<HR_work> getList() {
			return this.workRepository.findAll();
		}
		
		public Page<HR_work> searchAll(int page) {
			Pageable pageable = PageRequest.of(page, 10);
			return workRepository.findAll(pageable);
		}

		public HR_work getWorkById(int id) {
			return workRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("내역이 존재하지 않습니다."));
		}

// 근태내역 수정
		public void updateWork(int id, @Valid HR_workUpdateForm workUpdateForm) {
			HR_work work = getWorkById(id);
			HR_mem employee = memRepository.findById(workUpdateForm.getEmployeeId())
					.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));
			
			work.setName(employee.getName());
			work.setEmployeeId(employee);
			work.setEndTime(workUpdateForm.getEndTime());
			work.setOvertimeHour(workUpdateForm.getOvertimeHour());
			work.setOvertimePay(workUpdateForm.getOvertimePay());
			work.setOvertimeType(workUpdateForm.getOvertimeType());
			work.setStartTime(workUpdateForm.getStartTime());
			work.setWorkHour(workUpdateForm.getWorkHour());
			
			
			

	        workRepository.save(work);
		}

		public void deleteWork(int id) {
			workRepository.deleteById(id);
			
		}

}
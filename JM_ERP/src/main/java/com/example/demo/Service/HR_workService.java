package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;
import com.example.demo.Form.HR_vacationCreateForm;
import com.example.demo.Repository.HR_memRepository;
import com.example.demo.Repository.HR_workRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

	public void save(@Valid HR_vacationCreateForm vacationCreateForm) {
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
	    work.setAttendance(vacationCreateForm.getAttendance());
	    // 나머지 필드는 기본값이나 null 상태로 유지

	    // HR_work 저장
	    workRepository.save(work);
	}

	
}

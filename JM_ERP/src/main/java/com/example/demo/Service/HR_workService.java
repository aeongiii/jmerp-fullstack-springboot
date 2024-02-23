package com.example.demo.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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

		// 기존 출퇴근 또는 휴가 정보 삭제
		workRepository.deleteByEmployeeIdAndToday(employee, today);
		
		// HR_work 엔티티 찾기 또는 생성
		HR_work work = new HR_work();
		work.setEmployeeId(employee);
		work.setWorkDate(today);
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

		// 오늘 날짜 + 사원 이름과 맞는 행이 DB에 이미 있을 경우, 삭제
		workRepository.deleteByEmployeeIdAndToday(employee, today);

		// 새로운 휴가 정보 저장
		HR_work work = new HR_work();
		work.setName(employee.getName());
		work.setEmployeeId(employee);
		work.setWorkDate(today);
		work.setAttendance(vacationCreateForm.getAttendance());
		// 나머지 필드는 기본값이나 null 상태로 유지

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

	public Page<HR_work> searchAll(Pageable pageable) {
		return workRepository.findAll(pageable);
	}

	public HR_work getWorkById(int id) {
		return workRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("내역이 존재하지 않습니다."));
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

	// 월별 조회 로직 구현
	public Page<HR_work> searchByMonth(int year, int month, Pageable pageable) {
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
		return workRepository.findByWorkDateBetween(startDate, endDate, pageable);
	}

	// 사원별 조회 로직 구현
	public Page<HR_work> searchByEmployee(String employeeId, Pageable pageable) {
		return workRepository.findByEmployeeIdCustom(employeeId, pageable);
	}

}
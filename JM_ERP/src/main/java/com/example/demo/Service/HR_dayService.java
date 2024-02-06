package com.example.demo.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_cont;
import com.example.demo.Entity.HR_day;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_contUpdateForm;
import com.example.demo.Form.HR_dayCreateForm;
import com.example.demo.Form.HR_dayUpdateForm;
import com.example.demo.Repository.HR_dayRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_dayService {
	
	private final HR_dayRepository dayRepository;
	
	// HR_day 전체 리스트 출력
		public List<HR_day> getDayList() {
			return this.dayRepository.findAll();
		}

		public void saveDay(@Valid HR_dayCreateForm dayCreateForm) {
			// HR_day 엔티티 조회
			HR_day day = new HR_day();
			day.setDeptName(dayCreateForm.getDeptName());
			day.setDayWorkPay(dayCreateForm.getDayWorkPay());
			day.setDayWorkName(dayCreateForm.getDayWorkName());
			day.setDayWorkHour(dayCreateForm.getDayWorkHour());
			day.setDayWorkDate(dayCreateForm.getDayWorkDate());
			
			// HR_day 저장
			dayRepository.save(day);
		}
		


// 삭제
		public void deleteCont(int id) {
			dayRepository.deleteById(id);
			
		}

		public HR_day getDayById(int id) {
			return dayRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));
		}

// 수정
		public void updateDay(int id, @Valid HR_dayUpdateForm form) {
		HR_day day = getDayById(id);
		
		day.setDeptName(form.getDeptName());
		day.setDayWorkPay(form.getDayWorkPay());
		day.setDayWorkName(form.getDayWorkName());
		day.setDayWorkHour(form.getDayWorkHour());
		day.setDayWorkDate(form.getDayWorkDate());
		
		dayRepository.save(day);
			
		}
}

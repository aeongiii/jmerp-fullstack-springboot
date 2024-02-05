package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_day;
import com.example.demo.Form.HR_dayCreateForm;
import com.example.demo.Repository.HR_dayRepository;

import jakarta.validation.Valid;
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
}

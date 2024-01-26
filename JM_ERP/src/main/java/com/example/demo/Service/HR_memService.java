package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Repository.HR_deptRepository;
import com.example.demo.Repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_memService {
	
	private final HR_memRepository memRepository;
	private final HR_deptRepository deptRepository; // 클래스 레벨 변수로 선언
	
// HR_mem 전체 리스트 출력
	public List<HR_mem> getList() {
		return this.memRepository.findAll();
	}
	
// HR_dept 전체 리스트 출력
	public List<HR_dept> getdeptList() {
		return this.deptRepository.findAll();
	}
	
// 사원번호 자동 생성
	public String createEmployeeId(String deptName, LocalDate startDate) {
		// 입사연도 2자리 추출
		String yearPart = String.valueOf(startDate.getYear()).substring(2); 
		// 부서코드 2자리
        HR_dept dept = deptRepository.findByDeptName(deptName);
        String deptCode = dept != null ? dept.getDeptCode() : "00"; // 부서코드 기본값 00
        // 고유번호 3자리
        int count = (int) memRepository.count();
        String uniqueNumber = String.format("%03d", count + 1);
        		
        return yearPart + deptCode + uniqueNumber;
    }
	
// 사원 등록
	public void save(HR_memCreateForm form) {
		HR_mem newMember = new HR_mem();
        newMember.setEmployeeId(form.getEmployeeId()); // 컨트롤러에서 이미 생성된 사원번호 사용
        newMember.setName(form.getName());
        newMember.setEmail(form.getEmail());
        newMember.setPosition(form.getPosition());
        newMember.setStartDate(form.getStartDate());
        newMember.setBankNum(form.getBankNum());
        newMember.setRegularPay(form.getRegularPay());
        
        HR_dept dept = deptRepository.findByDeptName(form.getDeptName());
        if (dept != null) {
            newMember.setDeptName(dept);
        }

        memRepository.save(newMember);
	}
}

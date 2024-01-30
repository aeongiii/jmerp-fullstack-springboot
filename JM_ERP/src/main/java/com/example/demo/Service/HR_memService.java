package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_deptCreateForm;
import com.example.demo.Form.HR_memCreateForm;
import com.example.demo.Repository.HR_deptRepository;
import com.example.demo.Repository.HR_memRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_memService {
	
	private final HR_memRepository memRepository;
	private final HR_deptRepository deptRepository;

// HR_mem 전체 리스트 출력
	public List<HR_mem> getList() {
		return this.memRepository.findAll();
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
	
// 사원 검색	
	public List<HR_mem> search(String keyword) {
		// root : HR_mem 테이블 접근하는 기본 경로
		// cb : CriteriaBuilder 인스턴스 => 쿼리 조건 구성 시 사용됨
		Specification<HR_mem> spec = (root,query, cb) -> {	// 람다 표현식 :: (매개변수) -> { 쿼리문 조건(Predicate) 나열 }; 함수를 간결하게 정의하여 'HR_mem' 엔티티 필터링
//			Predicate employeeIdPredicate = cb.like(root.get("employeeId"), "%" + keyword + "%");
			Predicate bankNumPredicate = cb.like(root.get("bankNum"), "%" + keyword + "%");
			Predicate namePredicate = cb.like(root.get("name"), "%" + keyword + "%");
			Predicate emailPredicate = cb.like(root.get("email"), "%" + keyword + "%");
			Predicate positionPredicate = cb.like(root.get("position"), "%" + keyword + "%");
			// 부서명의 경우 외래키이므로 HR_dept와의 조인을 통해 검색
			Join<HR_mem, HR_dept> deptJoin = root.join("deptName");
			Predicate deptNamePredicate = cb.like(deptJoin.get("deptName"),  "%" + keyword + "%");

			return cb.or(bankNumPredicate, deptNamePredicate, namePredicate, emailPredicate, positionPredicate);
		};
		return memRepository.findAll(spec);
	}
	


}

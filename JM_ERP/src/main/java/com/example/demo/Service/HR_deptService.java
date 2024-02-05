package com.example.demo.Service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Repository.HR_deptRepository;
import com.example.demo.Repository.HR_memRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_deptService {
	
	private final HR_deptRepository deptRepository;
	private final HR_memRepository memRepository;

	// HR_dept 전체 리스트 출력
		public List<HR_dept> getdeptList() {
			List<HR_dept> deptList = deptRepository.findAll();
			// 부서코드 기준으로 정렬 : Comparator.comparing 메서드 참조 방식 -> 'deptList'의 'HR_dept'객체들을 'deptCode' 필드값에 따라 오름차순 정렬
			deptList.sort(Comparator.comparing(HR_dept::getDeptCode));
			return deptList;
		}
		
	// 부서 등록
		public void save2(String deptName) {
			// 부서코드 : 목록 개수 + 1만큼 자동 생성
			int deptCount = deptRepository.findAll().size();
			String deptCode = String.format("%02d", deptCount + 1);
			
			HR_dept newDept = new HR_dept();
		    newDept.setDeptName(deptName); // 컨트롤러에서 이미 생성된 사원번호 사용
		    newDept.setDeptCode(deptCode);
		        
		    deptRepository.save(newDept);
		}
		
	// 부서 수정
//		public void update2(String deptCode, String newDeptName) {
//			HR_dept dept = deptRepository.findByDeptCode(deptCode);
//			if (dept != null) {
//				dept.setDeptName(newDeptName);
//				deptRepository.save(dept);
//			}
//		}
	
		public HR_dept findByDeptName(String deptName) {
			return deptRepository.findByDeptName(deptName);
		}
		
		@Transactional
		public void updateDeptName(String oldDeptName, String newDeptName) {
			
//			 HR_dept oldDept = deptRepository.findByDeptName(oldDeptName);
			 HR_dept newDept = deptRepository.findByDeptName(newDeptName);
			
			 if (newDept == null) {
			        newDept = new HR_dept();
			        newDept.setDeptName(newDeptName);
			        deptRepository.save(newDept);
			    }
			 
			 HR_dept oldDept = deptRepository.findByDeptName(oldDeptName);
			    if (oldDept != null) {
			        memRepository.updateDeptName(newDept, oldDept);
			    } 
			 
			 
			 
// 2 
//			 List<HR_mem> employees = memRepository.findByDeptName(oldDept);
//			 for (HR_mem employee : employees) {
//			        employee.setDeptName(newDept);
//			    }
//			    memRepository.saveAll(employees);
//			    
//			    
//			    for (HR_mem employee : employees) {
//			        employee.setDeptName(newDept);
//			    }
//			    memRepository.saveAll(employees);
			    
			    
			    
// 1			    
//			// 1) old 부서에 속한 모든 사원의 부서정보를 new 부서로 변경
//			memRepository.updateDeptName(oldDeptName, newDeptName);
//			
//			// 2) new 부서 객체 생성하여 DB에 저장
//			HR_dept newDept = new HR_dept();
//			newDept.setDeptName(newDeptName);
//			deptRepository.save(newDept);
		}
		
	// 부서 삭제
		@Transactional
		public void delete2(String deptName) {
			deptRepository.deleteById(deptName);
			
		}
}

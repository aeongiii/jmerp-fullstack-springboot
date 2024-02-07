package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_cont;
import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_doc;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_docCreateForm;
import com.example.demo.Form.HR_docUpdateForm;
import com.example.demo.Repository.HR_docRepository;
import com.example.demo.Repository.HR_memRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_docService {
	private final HR_memRepository memRepository;
	private final HR_docRepository docRepository;

// 전체 리스트 출력
	public List<HR_doc> getDocList() {
		return docRepository.findAll();
	}

	
// 증명서 발행번호 자동 생성 (2024-1)
	public String createDocNum() {
		int year = LocalDate.now().getYear();
		int count = docRepository.countByyear(year);
		return year + "-" + (count + 1);
	}

	

// 증명서 등록
	public void saveDoc(@Valid HR_docCreateForm docCreateForm) {
		// HR_mem 엔티티 조회
		HR_mem employee = memRepository.findById(docCreateForm.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));

		// HR_doc 엔티티 설정
		HR_doc doc = new HR_doc();
		doc.setName(employee.getName());
		doc.setDocType(docCreateForm.getDocType());
		doc.setDocUse(docCreateForm.getDocUse());
		doc.setEmployeeId(employee);

		// 자동 생성한 증명서 발행번호
		String docNum = createDocNum();
		doc.setDocNum(docNum);
		
		// 나머지 필드는 기본값이나 null 상태로 유지

		// HR_doc 저장
		docRepository.save(doc);
	}

// 수정 : id로 증명서 찾기
	public HR_doc getDocById(String docNum) {
		return docRepository.findById(docNum)
				.orElseThrow(() -> new EntityNotFoundException("증명서가 존재하지 않습니다."));
	}


// 수정
	public void updateDoc(String docNum, @Valid HR_docUpdateForm form) {
		HR_doc doc = getDocById(docNum);
		HR_mem employee = memRepository.findById(form.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));
		
		doc.setName(employee.getName());
		doc.setEmployeeId(employee);
		doc.setDocType(form.getDocType());
		doc.setDocUse(form.getDocUse());
		doc.setDocUse(form.getDocUse());
		
		

        docRepository.save(doc);
	}
}

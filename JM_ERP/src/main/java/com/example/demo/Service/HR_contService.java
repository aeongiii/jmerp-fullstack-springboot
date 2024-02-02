package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_cont;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.HR_contCreateForm;
import com.example.demo.Repository.HR_contRepository;
import com.example.demo.Repository.HR_memRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_contService {

	private final HR_memRepository memRepository;
	private final HR_contRepository contRepository;

	public List<HR_cont> getcontList() {
		return contRepository.findAll();
	}

// 근로계약서 등록
	public void saveCont(@Valid HR_contCreateForm contCreateForm) {
		// HR_mem 엔티티 조회
		HR_mem employee = memRepository.findById(contCreateForm.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));

		// HR_cont 엔티티 설정
		HR_cont cont = new HR_cont();
		cont.setName(employee.getName());
		cont.setContractDate(contCreateForm.getContractDate());
		cont.setContractName(contCreateForm.getContractName());
		cont.setEmployeeId(employee);	// 사원번호를 이용하여 HR_mem의 객체를 조회, HR_cont에 저장
		cont.setSignA("요청완료".equals(contCreateForm.getSignA()));
		cont.setSignB("서명완료".equals(contCreateForm.getSignB()));
		// 나머지 필드는 기본값이나 null 상태로 유지

		// HR_cont 저장
		contRepository.save(cont);
	}

// 근로계약서 수정
	public HR_cont getContById(int id) {
		return contRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("근로계약서가 존재하지 않습니다."));
	}

	public void updateCont(int id, @Valid HR_contCreateForm form) {
		HR_cont cont = getContById(id);
		HR_mem employee = memRepository.findById(form.getEmployeeId())
				.orElseThrow(() -> new EntityNotFoundException("사원이 존재하지 않습니다."));
		
		cont.setEmployeeId(employee);
        cont.setContractDate(form.getContractDate());
        cont.setContractName(form.getContractName());
        cont.setSignA("요청완료".equals(form.getSignA()));
        cont.setSignB("서명완료".equals(form.getSignB()));

        contRepository.save(cont);
	}

}

package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Form.SD_memberCreateForm;
import com.example.demo.Repository.SD_MemberRepository;
import com.example.demo.Repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_MemberService {

	private final SD_MemberRepository memberRepository;
	private final SD_PurchaseRepository purchaseRepository;

// 고객 등록
	public void saveMember(SD_memberCreateForm form) {

		// 현재 날짜
		LocalDate today = LocalDate.now();

		SD_Member newMember = new SD_Member();
		newMember.setMemberAddress(form.getMemberAddress()); // 컨트롤러에서 이미 생성된 사원번호 사용
		newMember.setMemberEmail(form.getMemberEmail());
		newMember.setMemberId(form.getMemberId());
		newMember.setMemberJoinDate(today);
		newMember.setMemberName(form.getMemberName());
		newMember.setMemberPhoneNumber(form.getMemberPhoneNumber());

		memberRepository.save(newMember);
	}

// 전체 고객 조회	
	public Page<SD_Member> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return memberRepository.findAll(pageable);
	}	
	
	
// 구매내역 조회
	public Page<SD_Purchase> searchPurchase(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}

// 고객 등록 폼 저장
	public static void save(@Valid SD_memberCreateForm memCreateForm) {
		// TODO Auto-generated method stub

	}

// 고객 조회
	public List<SD_Member> findAllMembers() {
	    return memberRepository.findAll();
	}

//	public Page<SD_Member> searchMemberbyname(int page,String name){
//		Pageable pageable = PageRequest.of(page,10);
//		return memberRepository.findByName(name,pageable);
//		
//	}
}

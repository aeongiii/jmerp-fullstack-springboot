package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Entity.PC_PurchaseInquiry;
import com.example.demo.Repository.SD_MemberRepository;
import com.example.demo.Repository.PC_PurchaseInquiryRepository;
import com.example.demo.Repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_MemberService {

	private final SD_MemberRepository memberRepository;
	private final SD_PurchaseRepository purchaseRepository;
	
	
	public void RegMember(String memberId, String name, LocalDate dateOfBirth, String country, String contactNumber,
			String email, String address, String gender,boolean membership
			) {
		
		SD_Member m = new SD_Member();
		m.setAddress(address);
		m.setMemberId(memberId);
		m.setAddress(address);
		m.setContactNumber(contactNumber);
		m.setCountry(country);
		m.setDateOfBirth(dateOfBirth);
		m.setEmail(email);
		m.setGender(gender);
		m.setName(name);
		m.setJoinDate(LocalDate.now());
		m.setMembership(false);
		memberRepository.save(m);
	}
	
	public Page<SD_Member> searchMember(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return memberRepository.findAll(pageable);
		
		
	}
	
	public Page<SD_Purchase> searchPurchase(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}
}
package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Entity.Purchase;
import com.example.demo.Entity.PurchaseInquiry;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.Repository.PurchaseInquiryRepository;
import com.example.demo.Repository.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PurchaseRepository purchaseRepository;
	
	
	public void RegMember(String memberId, String name, LocalDate dateOfBirth, String country, String contactNumber,
			String email, String address, String gender,boolean membership
			) {
		
		Member m = new Member();
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
	
	public Page<Member> searchMember(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return memberRepository.findAll(pageable);
		
		
	}
	
	public Page<Purchase> searchPurchase(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}
}

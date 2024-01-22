package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	public void RegMember(String memberId, String name, LocalDate dateOfBirth, String country, String contactNumber,
			String email, String address, String gender,boolean membership
			) {
		
		Member m = new Member();
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
	
	public List<Member> searchMember() {
		return memberRepository.findAll();
	}
}

package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Repository.SD_MemberRepository;
import com.example.demo.Repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PurchaseService {
	
	private final SD_PurchaseRepository purchaseRepository;
	private final SD_MemberRepository memberRepository;

	// 고객 모두 조회 (페이징)
	public Page<SD_Purchase> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}
	
	
	
	// 고객 조회 로직 구현
		public Page<SD_Purchase> searchByMemberId(String memberId, Pageable pageable) {
			return purchaseRepository.findByMemberId(memberId, pageable);
		}

		public List<SD_Purchase> getList() {
			return this.purchaseRepository.findAll();
		}

}

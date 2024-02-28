
package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_sellerCreateForm;
import com.example.demo.Repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_SellerService {

	private final SD_SellerRepository sellerRepository;

// 판매자 등록 폼 저장
	public void saveSeller(@Valid SD_sellerCreateForm sellerCreateForm) {
		// 현재 날짜
		LocalDate today = LocalDate.now();

		SD_Seller newSeller = new SD_Seller();
		newSeller.setSellerAddress(sellerCreateForm.getSellerAddress()); // 컨트롤러에서 이미 생성된 사원번호 사용
		newSeller.setSellerEmail(sellerCreateForm.getSellerEmail());
		newSeller.setSellerId(sellerCreateForm.getSellerId());
		newSeller.setSellerJoinDate(today);
		newSeller.setSellerName(sellerCreateForm.getSellerName());
		newSeller.setSellerPhoneNumber(sellerCreateForm.getSellerPhoneNumber());

		sellerRepository.save(newSeller);

	}

// 전체 판매자 조회 (페이징)
	public Page<SD_Seller> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sellerRepository.findAll(pageable);
	}

// 판매자 조회
	public List<SD_Seller> findAllMembers() {
		return sellerRepository.findAll();
	}

// "000-00-00000"을 제외한 모든 판매자 조회 (/list 에서 사용)
	public Page<SD_Seller> findAllExceptSpecificSellerId(Pageable pageable) {
		return sellerRepository.findAllExceptSpecificSellerId(pageable);
	}

// "000-00-00000"을 제외한 모든 판매자 조회 (/search 에서 사용)
	public List<SD_Seller> findAllMembersExceptSelf() {
	    return sellerRepository.findAllExceptSpecificSellerId(Pageable.unpaged()).getContent();
	}
	

}
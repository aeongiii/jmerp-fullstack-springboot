
package com.example.demo.Service;

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

// 셀러 등록 폼 저장
	public static void save(@Valid SD_sellerCreateForm sellerCreateForm) {
		// TODO Auto-generated method stub

	}

	// 전체 고객 조회
	public Page<SD_Seller> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sellerRepository.findAll(pageable);
	}

}
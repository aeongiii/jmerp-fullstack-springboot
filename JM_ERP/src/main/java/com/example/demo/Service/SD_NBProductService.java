package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Repository.SD_NBProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_NBProductService {

	@Autowired
	private final SD_NBProductRepository nbRepository;
	
// 사업자등록번호로 판매내역 찾기
	public Page<SD_NBProduct> findBySellerId(String sellerId, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return nbRepository.findBySellerId(sellerId, pageable);
	}

	// 고객 모두 조회 (페이징)
		public Page<SD_NBProduct> searchAll(int page) {
			Pageable pageable = PageRequest.of(page, 10);
			return nbRepository.findAll(pageable);
		}
}

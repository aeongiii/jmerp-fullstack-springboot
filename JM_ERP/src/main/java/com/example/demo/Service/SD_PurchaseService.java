package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Purchase;
import com.example.demo.Repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PurchaseService {

	private final SD_PurchaseRepository purchaseRepository;

	// 고객 모두 조회 (페이징)
	public Page<SD_Purchase> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findAll(pageable);
	}

	public List<SD_Purchase> getList() {
		return this.purchaseRepository.findAll();
	}

	// 고객 조회 로직 구현
	public Page<SD_Purchase> searchByMemberId(String memberId, Pageable pageable) {
		return purchaseRepository.findByMemberId(memberId, pageable);
	}

	// 고객 아이디로 구매내역 찾기
	public Page<SD_Purchase> findByMemberId(String memberId, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseRepository.findByMemberId(memberId, pageable);
	}
	
	// productCode를 받아서 해당 상품의 총 구매수량 합계를 반환하는 메서드
    public int getAllTotalPurchaseEA(String productCode) {
        // SD_Purchase 테이블에서 productCode가 일치하는 모든 행을 찾음
        List<SD_Purchase> purchases = purchaseRepository.findByProductCode(productCode);
        
        // 찾은 행들에서 totalPurchaseEA 값들을 합산
        int total = purchases.stream().mapToInt(SD_Purchase::getTotalPurchaseEA).sum();
        
        return total;
    }
    
 // productCode를 받아서 해당 상품의 총 구매수량 합계를 반환하는 메서드
    public int getAllTotalPurchaseEA_memberId(String memberId) {
        // SD_Purchase 테이블에서 productCode가 일치하는 모든 행을 찾음
        List<SD_Purchase> purchases = purchaseRepository.findByMemberId(memberId);
        
        // 찾은 행들에서 totalPurchaseEA 값들을 합산
        int total = purchases.stream().mapToInt(SD_Purchase::getTotalPurchaseEA).sum();
        
        return total;
    }

}
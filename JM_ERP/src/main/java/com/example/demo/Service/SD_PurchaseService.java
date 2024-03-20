package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private final SD_NBProductService nbService;

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
    
 // 해당 멤버의 구매내역 --> 카테고리 비율을 반환하는 메서드
    public Map<String, Double> getProportion(String memberId) {
        List<Object[]> purchaseSummary = purchaseRepository.findPurchaseSummaryByMemberId(memberId);
        Map<String, Integer> categoryTotals = new HashMap<>();
        int totalPurchase = 0;

        for (Object[] summary : purchaseSummary) {
            String productCode = (String) summary[0];
            Integer totalPurchaseEA = ((Long) summary[1]).intValue();

            String category = nbService.findCategoryByProductCode(productCode);
            if (category != null) { // null 카테고리인 경우 포함 XX
                categoryTotals.merge(category, totalPurchaseEA, Integer::sum);
                totalPurchase += totalPurchaseEA;
            }
        }

        // 카테고리별 비율 계산
        Map<String, Double> categoryProportions = new HashMap<>();
        for (Map.Entry<String, Integer> entry : categoryTotals.entrySet()) {
            double proportion = ((double) entry.getValue() / totalPurchase) * 100; // 비율을 퍼센트로 계산
            categoryProportions.put(entry.getKey(), proportion);
        }

        return categoryProportions;
    }

}
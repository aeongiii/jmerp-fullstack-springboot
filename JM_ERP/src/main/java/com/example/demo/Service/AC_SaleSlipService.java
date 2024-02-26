package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Repository.AC_SaleSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_SaleSlipService {
	
	private final AC_SaleSlipRepository saleSlipRepository;
	
    public List<AC_SaleSlip> getList() {
        return this.saleSlipRepository.findAll();
    }
    
    public Page<AC_SaleSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.saleSlipRepository.findAll(pageable);
    }
    
    public List<AC_SaleSlip> save(List<Object> O) {
    	
    	List<AC_SaleSlip> list = new ArrayList<AC_SaleSlip>();
    	
//        String slipCode = ""; // 전표 코드
//
//        LocalDate tradeDate = ; // 거래일자
//        String trader = ; // 거래처
//        
//        String description = 상품명 갯수; // 적요
//        
//        Double amount = ; // 금액
//        Double VAT = ; // 부가세
//        String transactionType = 대행 판매 / 자사 상품 판매; // 거래유형
//
//        LocalDateTime createdAt = save 함수가 실행된 시점; // 생성일자
    	
    	
    	return new ArrayList<AC_SaleSlip>();
    }
}

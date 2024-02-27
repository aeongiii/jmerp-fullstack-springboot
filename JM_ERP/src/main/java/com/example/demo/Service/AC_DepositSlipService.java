package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Repository.AC_DepositSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_DepositSlipService {
	
	private final AC_DepositSlipRepository depositSlipRepository;
	
    public List<AC_DepositSlip> getList() {
        return this.depositSlipRepository.findAll();
    }
    
    public Page<AC_DepositSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findAll(pageable);
    }
    
//    public AC_DepositSlip save() { // 전표 갱신 시 마다 수익을 정산?	
//    	갱신시마다 각 판매 등록 업체로부터 팔린 갯 수 만큼 수익 정산, 자사물품 판매시에는 수익을 바로 정산
//    	즉 자사 물품 판매시에는 판매 전표와 동일한 결과가, 대행 판매 물품에 대해서는 다른 결과가 나오게 끔
//    	즉 판매수수료는 면세수익으로 표현 가능 과세물품, 면세물품
//
//    			new List<AC_SaleSlip> slip = new arrayList<AC_SaleSlip>();
//    		for (seller : SD_seller) {
//    		for(saleSlip : AC_saleSlip) {
//    			if (saleSlip.getCratedAT()) {
//    				continue;
//    			}
//    			
//    			if(NB & seller) {
//    				revenue += saleSlip.getAmount() / 20 (5%)
//    			}
//    			
//    			slip.setSlipCode()
//    			slip.setTradeDate(LocalDate.now)
//    			slip.setTrader(seller)
//    			slip.setDescription(대행판매 건수?)
//    			slip.setAmount(revenue)
//    			slip.setVAT(없음)
//    			slip.setTransactionType("판매수수료")
//    			slip.setCreatedAT(LocalDate.now())
//    		}
//
//    		NB가 아닌경우의 전표는 전표 코드를 제외한 saleSlip과 동일 - 어떻게 가져올 것인가
//
//    	}
//    }
}

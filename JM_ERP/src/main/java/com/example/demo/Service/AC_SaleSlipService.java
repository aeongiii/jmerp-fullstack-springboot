package com.example.demo.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Entity.SD_Purchase;
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
    
    // 갱신 버튼 만든뒤 되는지 확인 하기 - 현재 제일 맨 윗줄 데이터만 갱신 누를때마다 하나씩 추가되는 중
    public List<AC_SaleSlip> update(List<SD_Purchase> purchaseList) {
    	
    	List<AC_SaleSlip> updateSlipList = new ArrayList<AC_SaleSlip>();
    	
		List<AC_SaleSlip> slipList = getList();
	
		String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"+"MM"));
		
		int i = 1; // 전표 코드 숫자에 영향을 줌
		int j = 1; // 원래 있던 전표의 갯수 확인용
		
		for (SD_Purchase list : purchaseList) {
			
			AC_SaleSlip slips = new AC_SaleSlip();
			
			if (slipList.size() >= j) {
//				getList로 가져온 형식이 yyMM"숫자"이므로
				if ((slipList.get(j-1).getSlipCode().substring(0, 4)).equals(yearMonth)) {
					i++;
				}

				j++; // slipList의 갯수
				continue;
			}
// 판매내역이 slipList의 갯수를 넘어갔다면 갱신 시작	
			slips.setSlipCode(String.format("%s%03d", yearMonth, i));
			slips.setTradeDate(list.getPurchaseTime().toLocalDate());
			slips.setTrader(list.getMemberId().toString());
			slips.setDescription(list.getProductCode() + " " + list.getTotalPurchaseEA() + "개"); // 상품 코드로 저장됨 상품명을 가져올 방법을 찾을 것
			slips.setAmount(list.getTotalPrice());
			
			String product = list.getSellerName();    
			    
			String transactionType = "";
			
			if (!product.equals("달토끼")) {
			    transactionType = "대행판매";
			} else if (product.equals("달토끼")) {
    			transactionType = "자가판매";
			} else {
    			transactionType = "기타";
			}
			slips.setTransactionType(transactionType);
			slips.setCreatedAt(LocalDateTime.now()); // 나노초 까지 포함 됨 바꿀것
			
			updateSlipList.add(slips);
			i++;
		}
    	
    	return this.saleSlipRepository.saveAll(updateSlipList);
    }
}

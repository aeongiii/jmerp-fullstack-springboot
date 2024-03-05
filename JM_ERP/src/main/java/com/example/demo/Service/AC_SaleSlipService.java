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
import com.example.demo.Repository.SD_NBProductRepository;
import com.example.demo.Repository.SD_PBProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_SaleSlipService {
	
	private final AC_SaleSlipRepository saleSlipRepository;
	private final SD_PBProductRepository PBProductRepository;
	private final SD_NBProductRepository NBProductRepository;
	
    public List<AC_SaleSlip> getList() {
        return this.saleSlipRepository.findAll();
    }
    
    public Page<AC_SaleSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.saleSlipRepository.findAll(pageable);
    }
    
    public Page<AC_SaleSlip> searchTradeDateList(String keyword, int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return this.saleSlipRepository.findByTradeDateContaining(keyword, pageable);
    }
    
    public Page<AC_SaleSlip> searchTraderList(String keyword, int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return this.saleSlipRepository.findByTraderContaining(keyword, pageable);
    }
    
    public Page<AC_SaleSlip> searchSellerList(String keyword, int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return this.saleSlipRepository.findBySellerContaining(keyword, pageable);
    }
    
    public Page<AC_SaleSlip> searchDescriptionList(String keyword, int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return this.saleSlipRepository.findByDescriptionContaining(keyword, pageable);
    }
    
    public Page<AC_SaleSlip> searchTransactionTypeList(String keyword, int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return this.saleSlipRepository.findByTransactionTypeContaining(keyword, pageable);
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
// getList로 가져온 형식이 yyMM"숫자"이므로
				if ((slipList.get(j-1).getSlipCode().substring(0, 4)).equals(yearMonth)) {
					i++;
				}

				j++; // slipList의 갯수
				continue;
			}
// 판매내역이 slipList의 갯수를 넘어갔다면 갱신 시작	
	
			String ProductCode = list.getProductCode();

			String seller = list.getSellerName();
			    
			String transactionType = "";
			String product = "";
			
			if (!seller.equals("달토끼")) {
			    transactionType = "대행판매";
			    product = this.NBProductRepository.findProductNameByProductCode(ProductCode);
			} else if (seller.equals("달토끼")) {
    			transactionType = "자가판매";
    			product = this.PBProductRepository.findProductNameByProductCode(ProductCode);
			} else {
    			transactionType = "기타";
			}
			
			slips.setSlipCode(String.format("%s%03d", yearMonth, i));
			slips.setTradeDate(list.getPurchaseTime().toLocalDate());
			slips.setTrader(list.getMemberId().toString());
			slips.setProductCode(ProductCode);
			slips.setSeller(seller);
			slips.setDescription(product + " " + list.getTotalPurchaseEA() + "개");
			slips.setAmount(list.getTotalPrice());
			slips.setTransactionType(transactionType);
			slips.setCreatedAt(LocalDateTime.now()); // 나노초 까지 포함 됨 바꿀것
			
			updateSlipList.add(slips);
			i++;
		}
    	
    	return this.saleSlipRepository.saveAll(updateSlipList);
    }
}

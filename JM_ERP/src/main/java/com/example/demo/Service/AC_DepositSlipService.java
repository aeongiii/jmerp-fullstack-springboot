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

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Repository.AC_DepositSlipRepository;
import com.example.demo.Repository.SD_NBProductRepository;
import com.example.demo.Repository.SD_PBProductRepository;
import com.example.demo.Repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_DepositSlipService {
	
	private final AC_DepositSlipRepository depositSlipRepository;
	private final SD_NBProductRepository NBProductRepository;
	private final SD_PBProductRepository PBProductRepository;
	private final SD_SellerRepository sellerRepository;
	
    public List<AC_DepositSlip> getList() {
        return this.depositSlipRepository.findAll();
    }
    
    public Page<AC_DepositSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findAll(pageable);
    }
    
    public List<AC_DepositSlip> update(List<AC_SaleSlip> saleSlipList) { // 전표 갱신 시 마다 수익을 정산
//    	갱신시마다 각 판매 등록 업체로부터 팔린 갯 수 만큼 수익 정산
//    	판매수수료는 면세수익으로 표현 가능 과세물품, 면세물품

		List<AC_DepositSlip> updateSlipList = new ArrayList<AC_DepositSlip>();

		List<AC_DepositSlip> slipList = getList();    

		String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"+"MM"));
		
		int n = 0; // 반복 횟수    

		for (String seller : this.sellerRepository.findAllSellerName()) {

			int i = 1 + n; // 전표 코드 숫자에 영향을 줌
			int j = 1; // 전표의 갯수
			int k = 0; // 판매 건수 추적
			int revenue = 0; // 수익

			AC_DepositSlip slips = new AC_DepositSlip();

			for (AC_SaleSlip slip : saleSlipList) {
								
				if (slipList.size() >= j) {
					//getList로 가져온 형식이 yyMM"숫자"이므로
					if ((slipList.get(j-1).getSlipCode().substring(0, 4)).equals(yearMonth)) {
						i++;
					}
		
					j++;
					continue;
				}
				
    			if(seller.equals(this.NBProductRepository.findSellerNameByProductCode(slip.getDescription().split(" ")[0]))
    					&& !seller.equals("달토끼")) {
    				revenue += slip.getAmount() / 20; // (5%)
					k++;
    			}

				if(seller.equals(this.PBProductRepository.findSellerNameByProductCode(slip.getDescription().split(" ")[0]))
    					&& seller.equals("달토끼")) {
					revenue += slip.getAmount();
					k++;
				}
			}
			
			if (k == 0) {
				
				continue;
			}
			
			slips.setSlipCode(String.format("%s%03d", yearMonth, i));
			slips.setTradeDate(LocalDate.now());
			slips.setTrader(seller);
			slips.setDescription("판매 건수 : " + k + "건");
			slips.setAmount(revenue);
			slips.setTransactionType("판매수수료");
			slips.setCreatedAt(LocalDateTime.now());
			
			if(seller.equals("달토끼")) {
				
				slips.setTransactionType("자사품 판매 수익");
			}
					
			updateSlipList.add(slips);

			n++;
		}
	    return this.depositSlipRepository.saveAll(updateSlipList);
	}
}

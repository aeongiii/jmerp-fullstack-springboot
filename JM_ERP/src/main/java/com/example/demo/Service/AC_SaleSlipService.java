package com.example.demo.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
//    public List<AC_SaleSlip> save(List<SD_purchase> purchaseList) {
//    	
//    	List<AC_SaleSlip> list = new ArrayList<AC_SaleSlip>();
    	
//
//    		new List<AC_SaleSlip> slips = new ArrayList<AC_SaleSlip>();
    	
//			new List<AC_SaleSlip> slipList = getList();
    	
//			String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"+"MM"));
//			int i = 1;
//			int j = 1;
//    		for (list : purchaseList) {

//				if (getList().size() >= j) {
//					getList로 가져온 형식이 yyMM"숫자"이므로
//					if ((getList().get(j-1).subString(0,4)).equals(yearMonth)) {
//						i++;
//					}
    
//					j++;
// 					continue;
//    			}
    	
//    			slip.setSlipCode(String.format("%s%03d", yearMonth, i));
//    			slip.setTradeDate(list.getTradeDate);
//    			slip.setTrader(list.getMemberId);
//    			slip.setDescription(list.getProductCode() + " " + list.getProductEA() + "개");
//    			slip.setAmount(list.getPurchaseAmount);
//    			slip.setVAT(list.getPurchaseAmount / 10);
//				
//				String product = list.getSellerName();    
//				    
//    			if (!Seller.equals("달토끼")) {
//    			    transactionType = "대행판매";
//    			} else if (product.equals("달토끼")) {
//        			transactionType = "자가판매";
//    			} else {
//        			transactionType = "기타";
//    			}
//				slip.setTransactionType(TransactionType);
//    			slip.setCreatedAT(localDateTime.now());
//			}
//
//    	}
    	
//    	return this.saleSlipRepository.saveAll(slipList);
//    }
}

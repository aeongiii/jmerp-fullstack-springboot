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

import com.example.demo.Entity.AC_WithdrawalSlip;
import com.example.demo.Entity.PC_OrderSheet;
import com.example.demo.Repository.AC_WithdrawalSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_WithdrawalSlipService {
	
	private final AC_WithdrawalSlipRepository withdrawalSlipRepository;
	
    public List<AC_WithdrawalSlip> getList() {
        return this.withdrawalSlipRepository.findAll();
    }
    
    public Page<AC_WithdrawalSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findAll(pageable);
    }
    
    public Page<AC_WithdrawalSlip> searchTradeDateList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findByTradeDateContaining(keyword, pageable);
    }
    
    public Page<AC_WithdrawalSlip> searchTraderList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findByTraderContaining(keyword, pageable);
    }
    
	public Page<AC_WithdrawalSlip> searchDescriptionList(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 10);
	    return this.withdrawalSlipRepository.findByDescriptionContaining(keyword, pageable);
	}
    
	public Page<AC_WithdrawalSlip> searchTransactionTypeList(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 10);
	    return this.withdrawalSlipRepository.findByTransactionTypeContaining(keyword, pageable);
	}
	
	public List<AC_WithdrawalSlip> update(List<PC_OrderSheet> orderList) {
		
		List<AC_WithdrawalSlip> updateSlipList = new ArrayList<AC_WithdrawalSlip>();
		
		List<AC_WithdrawalSlip> slipList = getList();
		
		String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"+"MM"));
		
		int i = 1; // 전표 코드 숫자에 영향을 줌
		int j = 1; // 원래 있던 전표의 갯수 확인용
		
		for (PC_OrderSheet list : orderList) {
			
			AC_WithdrawalSlip slips = new AC_WithdrawalSlip();
			
			if (slipList.size() >= j) {
// getList로 가져온 형식이 yyMM"숫자"이므로
				if ((slipList.get(j-1).getSlipCode().substring(0, 4)).equals(yearMonth)) {
					i++;
				}

				j++; // slipList의 갯수
				continue;
			}
// 판매내역이 slipList의 갯수를 넘어갔다면 갱신 시작
			
			slips.setSlipCode(String.format("%sW%03d", yearMonth, i));
			slips.setTradeDate(list.getDeliveryDate());
			slips.setTrader(list.getClientName());
			slips.setDescription(list.getItem() + " " + list.getCount().intValue() + "개");
			slips.setAmount(list.getTotalPrice().intValue());
			slips.setTransactionType("원재료 구입");
			slips.setCreatedAt(LocalDateTime.now());
			
			updateSlipList.add(slips);
			i++;
		}
    	
    	return this.withdrawalSlipRepository.saveAll(updateSlipList);
	}
}

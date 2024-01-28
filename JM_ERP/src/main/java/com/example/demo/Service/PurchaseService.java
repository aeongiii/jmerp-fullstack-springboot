package com.example.demo.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.OrderSheet;
import com.example.demo.Entity.PurchaseInquiry;
import com.example.demo.Repository.OrderSheetRepository;
import com.example.demo.Repository.PurchaseInquiryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PurchaseService {

	private final OrderSheetRepository orderSheetRepository;
	private final PurchaseInquiryRepository purchaseInquiryRepository;

	
	public Page<OrderSheet> searchOrderSheet(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return orderSheetRepository.findAll(pageable);
	}
	
	
	public Page<PurchaseInquiry> searchPurchaseInquiry(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseInquiryRepository.findAll(pageable);
	}

}


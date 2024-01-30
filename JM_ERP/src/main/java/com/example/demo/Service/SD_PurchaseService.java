package com.example.demo.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.PC_OrderSheet;
import com.example.demo.Entity.PC_PurchaseInquiry;
import com.example.demo.Repository.PC_OrderSheetRepository;
import com.example.demo.Repository.PC_PurchaseInquiryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PurchaseService {

	private final PC_OrderSheetRepository orderSheetRepository;
	private final PC_PurchaseInquiryRepository purchaseInquiryRepository;

	
	public Page<PC_OrderSheet> searchOrderSheet(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return orderSheetRepository.findAll(pageable);
	}
	
	
	public Page<PC_PurchaseInquiry> searchPurchaseInquiry(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseInquiryRepository.findAll(pageable);
	}
	

}


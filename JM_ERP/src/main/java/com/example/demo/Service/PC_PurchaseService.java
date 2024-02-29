package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.PC_OrderSheet;
import com.example.demo.Entity.PC_PurchaseInquiry;
import com.example.demo.Repository.PC_OrderSheetRepository;
import com.example.demo.Repository.PC_PurchaseInquiryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PC_PurchaseService {

	private final PC_OrderSheetRepository orderSheetRepository;
	private final PC_PurchaseInquiryRepository purchaseInquiryRepository;

	public Page<PC_OrderSheet> searchOrderSheet(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return orderSheetRepository.findAll(pageable);
	}

	public void orderSheetSave(String clinetName, String contactPerson, String item, LocalDate deliveryDate,
			Double Count,String completionStatus, Long totalPrice ,String bool,Long num) {

		PC_OrderSheet order = new PC_OrderSheet();
		Optional<PC_PurchaseInquiry> purchase = findpurchase(num);
		
		purchase.get().setAcceptance(bool);
		
		order.setClientName(clinetName);
		order.setContactPerson(contactPerson);
		order.setItem(item);
		order.setDeliveryDate(deliveryDate);
		order.setCount(Count);
		order.setCompletionStatus(completionStatus);
		order.setTotalPrice(totalPrice);
		
		
		orderSheetRepository.save(order);
		purchaseInquiryRepository.save(purchase.get());
		
		
		
		
		
		
		
		
	}

	public Page<PC_PurchaseInquiry> searchPurchaseInquiry(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return purchaseInquiryRepository.findAll(pageable);
	}

	public Optional<PC_PurchaseInquiry> findpurchase(Long id) {
		return purchaseInquiryRepository.findById(id);

	}

	public void purchaseSave(LocalDate PurchaseDate, String clientName, String itemName, Double totalCount,
			String warehouseName, String acceptance) {
		PC_PurchaseInquiry purchase = new PC_PurchaseInquiry();

		purchase.setPurchaseDate(PurchaseDate);
		purchase.setClientName(clientName);
		purchase.setItemName(itemName);
		purchase.setTotalCount(totalCount);
		purchase.setWarehouseName(warehouseName);
		purchase.setAcceptance(acceptance);
		purchaseInquiryRepository.save(purchase);
	}

}

package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Seller;
import com.example.demo.Repository.SellerRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class SellerService {
	
	private final SellerRepository sellerRepository;
	
	public void RegSeller(String sellerId, String businessNumber, String contactInfo, String email, String ceoName,
			String address, boolean contract) {
		
		Seller newSeller = new Seller();
        
		newSeller.setSellerId(sellerId);
        newSeller.setBusinessNumber(businessNumber);
        newSeller.setContactInfo(contactInfo);
        newSeller.setEmail(email);
        newSeller.setCeoName(ceoName);
        newSeller.setAddress(address);
        newSeller.setContract(contract);
        newSeller.setRegistrationDate(LocalDate.now());

        sellerRepository.save(newSeller);
	}
	
	public Page<Seller> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sellerRepository.findAll(pageable);
	}
}

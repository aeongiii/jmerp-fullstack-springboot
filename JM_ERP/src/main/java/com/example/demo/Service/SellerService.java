package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.CommissionRate;
import com.example.demo.Entity.Sales;
import com.example.demo.Entity.Seller;
import com.example.demo.Entity.SellerCommission;
import com.example.demo.Repository.CommissionRateRepository;
import com.example.demo.Repository.SalesRepository;
import com.example.demo.Repository.SellerCommissionRepository;
import com.example.demo.Repository.SellerRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SellerService {
	
	private final SellerRepository sellerRepository;
	private final SellerCommissionRepository sellerCommissionRepository;
	private final CommissionRateRepository commissionRateRepository;
	private final SalesRepository salesrepository;
	
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
	
	public Page<SellerCommission> searchCommssion(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sellerCommissionRepository.findAll(pageable);
	}
	
	public Page<CommissionRate> searchCommssionRate(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return commissionRateRepository.findAll(pageable);
	}
	
	public Page<Sales> searchSales(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return salesrepository.findAll(pageable);
	}
	
}

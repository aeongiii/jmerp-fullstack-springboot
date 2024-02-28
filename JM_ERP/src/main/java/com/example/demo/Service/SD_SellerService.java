//package com.example.demo.Service;
//
//import java.time.LocalDate;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.Entity.SD_CommissionRate;
//import com.example.demo.Entity.SD_Sales;
//import com.example.demo.Entity.SD_Seller;
//import com.example.demo.Entity.SD_SellerCommission;
//import com.example.demo.Repository.SD_CommissionRateRepository;
//import com.example.demo.Repository.SD_SalesRepository;
//import com.example.demo.Repository.SD_SellerCommissionRepository;
//import com.example.demo.Repository.SD_SellerRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Service
//public class SD_SellerService {
//
//	private final SD_SellerRepository sellerRepository;
//	private final SD_SellerCommissionRepository sellerCommissionRepository;
//	private final SD_CommissionRateRepository commissionRateRepository;
//	private final SD_SalesRepository salesrepository;
//
//	public void RegSeller(String sellerId, String businessNumber, String contactInfo, String email, String ceoName,
//			String address) {
//
//		SD_Seller newSeller = new SD_Seller();
//
//		newSeller.setSellerId(sellerId);
//		newSeller.setBusinessNumber(businessNumber);
//		newSeller.setContactInfo(contactInfo);
//		newSeller.setEmail(email);
//		newSeller.setCeoName(ceoName);
//		newSeller.setAddress(address);
//		newSeller.setContract(true);
//		newSeller.setRegistrationDate(LocalDate.now());
//
//		sellerRepository.save(newSeller);
//	}
//
//	public Page<SD_Seller> searchAll(int page) {
//		Pageable pageable = PageRequest.of(page, 10);
//		return sellerRepository.findAll(pageable);
//	}
//
//	public Page<SD_SellerCommission> searchCommssion(int page) {
//		Pageable pageable = PageRequest.of(page, 10);
//		return sellerCommissionRepository.findAll(pageable);
//	}
//
//	public Page<SD_CommissionRate> searchCommssionRate(int page) {
//		Pageable pageable = PageRequest.of(page, 10);
//		return commissionRateRepository.findAll(pageable);
//	}
//
//	public Page<SD_Sales> searchSales(int page) {
//		Pageable pageable = PageRequest.of(page, 10);
//		return salesrepository.findAll(pageable);
//	}
//
//	/** 카테고리별 수수료를 등로하는 함수 */
//	public void setCommssionRate(String category, Double Rate) {
//
//		SD_CommissionRate commisstionRate = new SD_CommissionRate();
//		commisstionRate.setCategory(category);
//		commisstionRate.setRate(Rate);
//
//		commissionRateRepository.save(commisstionRate);
//	}
//
//}

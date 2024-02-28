package com.example.demo.Service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_Member;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_sellerCreateForm;
import com.example.demo.Repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_SellerService {

	private final SD_SellerRepository sellerRepository;

// 셀러 등록 폼 저장
	public static void save(@Valid SD_sellerCreateForm sellerCreateForm) {
		// TODO Auto-generated method stub

	}

	// 전체 고객 조회
	public Page<SD_Seller> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sellerRepository.findAll(pageable);
	}

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

}

package com.example.demo.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_NBProduct;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_NBProductCreateForm;
import com.example.demo.Repository.SD_NBProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_NBProductService {

	@Autowired
	private final SD_NBProductRepository nbRepository;
	private final SD_SellerService sellerService;
	
// 사업자등록번호로 NB판매내역 찾기
	public Page<SD_NBProduct> findBySellerId(String sellerId, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return nbRepository.findBySellerId(sellerId, pageable);
	}

// NB 모두 조회 (페이징)
	public Page<SD_NBProduct> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return nbRepository.findAll(pageable);
	}

// NB상품 등록 폼 저장
	public void saveNBProduct(SD_NBProductCreateForm form) {

		// 현재 날짜
		LocalDate today = LocalDate.now();

		// 새로운 상품 객체 생성
		SD_NBProduct newNB = new SD_NBProduct();
		
		// sellerName을 이용하여 sellerID 조회
		String strSellerId = sellerService.findSellerIdBySellerName(form.getSellerName());
		SD_Seller sellerId = sellerService.findById(strSellerId);
		newNB.setSellerId(sellerId);
		
		newNB.setSellerName(form.getSellerName());
		newNB.setCategory(form.getCategory()); 
		newNB.setProductName(form.getProductName());
		newNB.setProductCode(form.getProductCode());
		newNB.setPriceEA(form.getPriceEA());
//		newNB.setTotalSaleEA(form.getTotalSaleEA());
//		newNB.setTotalPrice(form.getTotalPrice());
		newNB.setRegistrationDate(today);
		newNB.setImageFileLink(form.getImageFileLink());
		newNB.setDescription(form.getDescription());
		
		// 일단 저장
		SD_NBProduct savedNB = nbRepository.save(newNB);
		
		// 저장 후 생성된 id를 이용하여 상품코드 생성
		String productCode = "nb_" + savedNB.getId();
	    savedNB.setProductCode(productCode);
		
//		--> 코드 업데이트 위해 다시 저장
	    nbRepository.save(savedNB);
		
	}


}
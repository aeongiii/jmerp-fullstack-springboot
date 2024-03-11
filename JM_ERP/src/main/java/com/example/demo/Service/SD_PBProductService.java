package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_PBProduct;
import com.example.demo.Entity.SD_Seller;
import com.example.demo.Form.SD_PBProductCreateForm;
import com.example.demo.Repository.SD_PBProductRepository;
import com.example.demo.Repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PBProductService {

	@Autowired
	private final SD_PBProductRepository pbRepository;
	private final SD_SellerService sellerService;

// 자체상품 모두 조회(페이징)
	public Page<SD_PBProduct> searchAll(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return pbRepository.findAll(pageable);
	}

// 자체상품 등록 폼 저장
	public void savePBProduct(@Valid SD_PBProductCreateForm form) {


		// 현재 날짜
		LocalDate today = LocalDate.now();

		// 새로운 상품 객체 생성
		SD_PBProduct newPB = new SD_PBProduct();
		
		// html로부터 숨겨진 필드로 전달받은 sellerName을 이용하여 sellerID 조회
		String strSellerId = sellerService.findSellerIdBySellerName(form.getSellerName());
		SD_Seller sellerId = sellerService.findById(strSellerId);
		newPB.setSellerId(sellerId);
		
//		newPB.setSellerName(form.getSellerName());
//		newPB.setTotalSaleEA(form.getTotalSaleEA());
//		newPB.setTotalPrice(form.getTotalPrice());
		
		newPB.setCategory(form.getCategory()); 
		newPB.setProductName(form.getProductName());
		newPB.setProductCode(form.getProductCode());
		newPB.setPriceEA(form.getPriceEA());
		newPB.setRegistrationDate(today);
		newPB.setImageFileLink(form.getImageFileLink());
		newPB.setDescription(form.getDescription());
		
		// 일단 저장
		SD_PBProduct savedPB = pbRepository.save(newPB);
		
		// 저장 후 생성된 id를 이용하여 상품코드 생성
		String productCode = "A" + String.format("%03d", savedPB.getId());
	    savedPB.setProductCode(productCode);
		
//		--> 코드 업데이트 위해 다시 저장
	    pbRepository.save(savedPB);
		
	}

	public List<SD_PBProduct> getList() {
		List<SD_PBProduct> pbList = pbRepository.findAll();
		return pbList;
	}
}

package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.SD_Seller;

public interface SD_SellerRepository extends JpaRepository<SD_Seller, String>{

	Page<SD_Seller> findAll(Pageable pageable);
	
	// 사업자등록번호 '000-00-00000'을 제외하고 나머지 데이터 추출
	 @Query("SELECT s FROM SD_Seller s WHERE s.sellerId <> '000-00-00000'")
	    Page<SD_Seller> findAllExceptSpecificSellerId(Pageable pageable);
	    
	//회계 전표에서 사용  
	    @Query("SELECT s.sellerName FROM SD_Seller s")
	    List<String> findAllSellerName();
}

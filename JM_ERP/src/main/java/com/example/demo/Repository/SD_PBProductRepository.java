package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.SD_PBProduct;

public interface SD_PBProductRepository extends JpaRepository<SD_PBProduct, Long> {
	
    Page<SD_PBProduct> findAll(Pageable pageable);
    
    // 회계에서 사용
    @Query("SELECT pb.sellerName FROM SD_PBProduct pb WHERE pb.productCode = :productCode")
    String findSellerNameByProductCode(@Param("productCode") String productCode);
}

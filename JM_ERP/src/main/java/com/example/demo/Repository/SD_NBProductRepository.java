
package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.SD_NBProduct;

public interface SD_NBProductRepository extends JpaRepository<SD_NBProduct, Long> {
	
    Page<SD_NBProduct> findAll(Pageable pageable);

 // 사업자등록번호에 따라 조회
    @Query("SELECT nb FROM SD_NBProduct nb WHERE nb.sellerId.sellerId = :sellerId")
    Page<SD_NBProduct> findBySellerId(@Param("sellerId")String sellerId, Pageable pageable);


}

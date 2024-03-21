
package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.SD_Purchase;

public interface SD_PurchaseRepository extends JpaRepository<SD_Purchase, Long> {
	
    Page<SD_Purchase> findAll(Pageable pageable);

    // 고객id에 따라 조회
    @Query("SELECT p FROM SD_Purchase p WHERE p.memberId.memberId = :memberId")
    Page<SD_Purchase> findByMemberId(@Param("memberId")String memberId, Pageable pageable);

    
    // memberId를 통해 구매 리스트 조회
    @Query("SELECT p FROM SD_Purchase p WHERE p.memberId.memberId = :memberId")
    List<SD_Purchase> findByMemberId(@Param("memberId") String memberId);
    
 // 주어진 productCode에 해당하는 구매 내역 조회
    List<SD_Purchase> findByProductCode(String productCode);
    
 // memberId에 따라 productCode별로 totalPurchaseEA를 합산
    @Query("SELECT p.productCode, SUM(p.totalPurchaseEA) FROM SD_Purchase p WHERE p.memberId.memberId = :memberId GROUP BY p.productCode")
    List<Object[]> findPurchaseSummaryByMemberId(@Param("memberId") String memberId);

}

package com.example.demo.Repository;

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

}


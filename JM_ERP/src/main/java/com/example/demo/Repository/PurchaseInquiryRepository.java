package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PurchaseInquiry;

public  interface PurchaseInquiryRepository extends JpaRepository<PurchaseInquiry, Long> {
	Page<PurchaseInquiry> findAll(Pageable pageable);
}

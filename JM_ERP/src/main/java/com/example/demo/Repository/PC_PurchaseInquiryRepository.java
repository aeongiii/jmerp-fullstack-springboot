package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PC_PurchaseInquiry;

public  interface PC_PurchaseInquiryRepository extends JpaRepository<PC_PurchaseInquiry, Long> {
	Page<PC_PurchaseInquiry> findAll(Pageable pageable);
	
	Optional<PC_PurchaseInquiry> findById(Long id);


}

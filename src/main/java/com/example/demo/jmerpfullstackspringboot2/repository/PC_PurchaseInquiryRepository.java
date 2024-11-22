package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.PC_PurchaseInquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PC_PurchaseInquiryRepository extends JpaRepository<PC_PurchaseInquiry, Long> {
    Page<PC_PurchaseInquiry> findAll(Pageable pageable);

    Optional<PC_PurchaseInquiry> findById(Long id);


}

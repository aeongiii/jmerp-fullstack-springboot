package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.PC_OrderSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PC_OrderSheetRepository extends JpaRepository<PC_OrderSheet, Long> {
    Page<PC_OrderSheet> findAll(Pageable pageable);
}

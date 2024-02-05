package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.PC_OrderSheet;

public interface PC_OrderSheetRepository extends JpaRepository<PC_OrderSheet, Long> {
    Page<PC_OrderSheet> findAll(Pageable pageable);
}

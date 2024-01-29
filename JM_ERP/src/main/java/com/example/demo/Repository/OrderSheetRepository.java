package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.OrderSheet;

public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {
    Page<OrderSheet> findAll(Pageable pageable);
}

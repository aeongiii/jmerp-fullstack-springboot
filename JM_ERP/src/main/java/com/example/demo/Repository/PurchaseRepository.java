package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Page<Purchase> findAll(Pageable pageable);
}


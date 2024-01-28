package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SellerCommission;

public interface SellerCommissionRepository extends JpaRepository<SellerCommission, Long> {
    Page<SellerCommission> findAll(Pageable pageable);
}


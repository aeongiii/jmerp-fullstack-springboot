package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_SellerCommission;

public interface SD_SellerCommissionRepository extends JpaRepository<SD_SellerCommission, Long> {
    Page<SD_SellerCommission> findAll(Pageable pageable);
}


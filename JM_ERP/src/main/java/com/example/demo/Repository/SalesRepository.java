package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    Page<Sales> findAll(Pageable pageable);
}


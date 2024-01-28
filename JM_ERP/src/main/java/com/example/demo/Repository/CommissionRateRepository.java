package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.CommissionRate;

public interface CommissionRateRepository extends JpaRepository<CommissionRate, String> {
    Page<CommissionRate> findAll(Pageable pageable);
}

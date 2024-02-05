package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.SD_CommissionRate;

public interface SD_CommissionRateRepository extends JpaRepository<SD_CommissionRate, String> {
    Page<SD_CommissionRate> findAll(Pageable pageable);
}

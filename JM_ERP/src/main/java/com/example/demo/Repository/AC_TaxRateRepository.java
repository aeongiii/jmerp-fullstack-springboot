package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_TaxRate;

@Repository
public interface AC_TaxRateRepository extends JpaRepository<AC_TaxRate, Long> {

}

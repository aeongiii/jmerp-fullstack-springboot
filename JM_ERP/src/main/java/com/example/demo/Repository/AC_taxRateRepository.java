package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_taxRate;

@Repository
public interface AC_taxRateRepository extends JpaRepository<AC_taxRate, Long> {

}

package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AC_TaxRateRepository extends JpaRepository<AC_TaxRate, Long> {

}

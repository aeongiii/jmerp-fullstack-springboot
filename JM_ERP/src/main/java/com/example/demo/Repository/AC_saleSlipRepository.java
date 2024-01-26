package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_saleSlip;

@Repository
public interface AC_saleSlipRepository extends JpaRepository<AC_saleSlip, String> {

}

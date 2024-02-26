package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Entity.AC_SaleSlip;

@Repository
public interface AC_SaleSlipRepository extends JpaRepository<AC_SaleSlip, String> {

    Page<AC_SaleSlip> findAll(Pageable pageable);
}

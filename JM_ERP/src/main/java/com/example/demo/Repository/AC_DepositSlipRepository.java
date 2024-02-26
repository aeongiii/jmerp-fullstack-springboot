package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_DepositSlip;

@Repository
public interface AC_DepositSlipRepository extends JpaRepository<AC_DepositSlip, String> {
	
    Page<AC_DepositSlip> findAll(Pageable pageable);
}

package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_WithdrawalSlip;

@Repository
public interface AC_WithdrawalSlipRepository extends JpaRepository<AC_WithdrawalSlip, String> {
	
    Page<AC_WithdrawalSlip> findAll(Pageable pageable);
}

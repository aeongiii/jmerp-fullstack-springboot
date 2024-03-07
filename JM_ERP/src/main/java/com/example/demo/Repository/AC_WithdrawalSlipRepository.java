package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_WithdrawalSlip;

@Repository
public interface AC_WithdrawalSlipRepository extends JpaRepository<AC_WithdrawalSlip, String> {
	
    Page<AC_WithdrawalSlip> findAll(Pageable pageable);
    
    @Query("SELECT w FROM AC_WithdrawalSlip w WHERE CAST(w.tradeDate AS string) LIKE %:keyword%")
    Page<AC_WithdrawalSlip> findByTradeDateContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Page<AC_WithdrawalSlip> findByTraderContaining(String keyword, Pageable pageable);
    
    Page<AC_WithdrawalSlip> findByDescriptionContaining(String keyword, Pageable pageable);
    
    Page<AC_WithdrawalSlip> findByTransactionTypeContaining(String keyword, Pageable pageable);
    
    List<AC_WithdrawalSlip> findBySlipCodeContaining(String keyword);
}

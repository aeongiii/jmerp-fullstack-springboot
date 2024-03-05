package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_DepositSlip;

@Repository
public interface AC_DepositSlipRepository extends JpaRepository<AC_DepositSlip, String> {
	
    Page<AC_DepositSlip> findAll(Pageable pageable);
    
    @Query("SELECT d FROM AC_DepositSlip d WHERE CAST(d.tradeDate AS string) LIKE %:keyword%")
    Page<AC_DepositSlip> findByTradeDateContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Page<AC_DepositSlip> findByTraderContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Page<AC_DepositSlip> findByDescriptionContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Page<AC_DepositSlip> findByTransactionTypeContaining(@Param("keyword") String keyword, Pageable pageable);
}

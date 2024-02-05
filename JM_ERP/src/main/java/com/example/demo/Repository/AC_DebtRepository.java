package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_Debt;

@Repository
public interface AC_DebtRepository extends JpaRepository<AC_Debt, String> {
	
    @Query("SELECT MAX(debt.debtNumber) FROM AC_Debt debt")
    String findMaxDebtNumber();
    
    Page<AC_Debt> findAll(Pageable pageable);
}

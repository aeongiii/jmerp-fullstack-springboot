package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_debt;

@Repository
public interface AC_debtRepository extends JpaRepository<AC_debt, String> {
	
    @Query("SELECT MAX(debt.debtNumber) FROM AC_debt debt")
    String findMaxDebtNumber();
}

package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_Debt;

@Repository
public interface AC_DebtRepository extends JpaRepository<AC_Debt, String> {
	
    @Query("SELECT MAX(debt.debtNumber) FROM AC_Debt debt")
    String findMaxDebtNumber();
    
    Page<AC_Debt> findAll(Pageable pageable);
    
    // :debtNumber 가 @Param("debtNumber")에 대응 됨
    @Query("SELECT debt.description FROM AC_Debt debt WHERE debt.debtNumber = :debtNumber")
    List<String> findDescriptionByDebtNumber(@Param("debtNumber") String debtNumber);
    
    @Query("SELECT d FROM AC_Debt d WHERE CAST(d.date AS string) LIKE %:keyword%")
    Page<AC_Debt> findByDateContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Page<AC_Debt> findByTraderContaining(String keyword, Pageable pageable);
    
    @Query("SELECT d FROM AC_Debt d WHERE CAST(d.amount AS string) LIKE %:keyword%")
    Page<AC_Debt> findByAmountContaining(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT d FROM AC_Debt d WHERE CAST(d.maturityDate AS string) LIKE %:keyword%")
    Page<AC_Debt> findByMaturityDateContaining(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT d FROM AC_Debt d WHERE CAST(d.description AS string) LIKE %:keyword%")
    Page<AC_Debt> findByDescriptionContaining(@Param("keyword") String keyword, Pageable pageable);
}

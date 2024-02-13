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
}

package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_Bond;

@Repository
public interface AC_BondRepository extends JpaRepository<AC_Bond, String> {
	
	// AC_bond 뒤에 붙는 bond 는 별칭 MAX() 안에 있는 bond. 를 사용할 수 있게 함
    @Query("SELECT MAX(bond.bondNumber) FROM AC_Bond bond") 
    String findMaxBondNumber();
    
    Page<AC_Bond> findAll(Pageable pageable);
    
    // :bondNumber 가 @Param("bondNumber")에 대응 됨
    @Query("SELECT bond.description FROM AC_Bond bond WHERE bond.bondNumber = :bondNumber")
    List<String> findDescriptionByBondNumber(@Param("bondNumber") String bondNumber);
}

package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_Bond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AC_BondRepository extends JpaRepository<AC_Bond, String> {

    // AC_bond 뒤에 붙는 bond 는 별칭 MAX() 안에 있는 bond. 를 사용할 수 있게 함
    @Query("SELECT MAX(bond.bondNumber) FROM AC_Bond bond")
    String findMaxBondNumber();

    Page<AC_Bond> findAll(Pageable pageable);

    // :bondNumber 가 @Param("bondNumber")에 대응 됨
    @Query("SELECT bond.description FROM AC_Bond bond WHERE bond.bondNumber = :bondNumber")
    List<String> findDescriptionByBondNumber(@Param("bondNumber") String bondNumber);

    @Query("SELECT b FROM AC_Bond b WHERE CAST(b.date AS string) LIKE %:keyword%")
    Page<AC_Bond> findByDateContaining(@Param("keyword") String keyword, Pageable pageable);

    Page<AC_Bond> findByTraderContaining(String keyword, Pageable pageable);

    @Query("SELECT b FROM AC_Bond b WHERE CAST(b.amount AS string) LIKE %:keyword%")
    Page<AC_Bond> findByAmountContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM AC_Bond b WHERE CAST(b.maturityDate AS string) LIKE %:keyword%")
    Page<AC_Bond> findByMaturityDateContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM AC_Bond b WHERE CAST(b.description AS string) LIKE %:keyword%")
    Page<AC_Bond> findByDescriptionContaining(@Param("keyword") String keyword, Pageable pageable);
}

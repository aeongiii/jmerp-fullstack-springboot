package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_SaleSlip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AC_SaleSlipRepository extends JpaRepository<AC_SaleSlip, String> {

    Page<AC_SaleSlip> findAll(Pageable pageable);

    @Query("SELECT s FROM AC_SaleSlip s WHERE CAST(s.tradeDate AS string) LIKE %:keyword%")
    Page<AC_SaleSlip> findByTradeDateContaining(@Param("keyword") String keyword, Pageable pageable);

    Page<AC_SaleSlip> findByTraderContaining(String keyword, Pageable pageable);

    Page<AC_SaleSlip> findBySellerContaining(String keyword, Pageable pageable);

    Page<AC_SaleSlip> findByDescriptionContaining(String keyword, Pageable pageable);

    Page<AC_SaleSlip> findByTransactionTypeContaining(String keyword, Pageable pageable);
}

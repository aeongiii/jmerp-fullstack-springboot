package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_purchaseSlip;

@Repository
public interface AC_purchaseSlipRepository extends JpaRepository<AC_purchaseSlip, String> {

}

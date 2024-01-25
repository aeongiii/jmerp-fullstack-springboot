package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_month;

@Repository
public interface AC_monthRepository extends JpaRepository<AC_month, String> {

}

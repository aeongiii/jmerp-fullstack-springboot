package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_Month;

@Repository
public interface AC_MonthRepository extends JpaRepository<AC_Month, String> {
	
	AC_Month findByMonthId(String keyword);
}

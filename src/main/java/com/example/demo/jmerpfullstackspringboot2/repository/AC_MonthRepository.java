package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AC_MonthRepository extends JpaRepository<AC_Month, String> {

    AC_Month findByMonthId(String keyword);
}

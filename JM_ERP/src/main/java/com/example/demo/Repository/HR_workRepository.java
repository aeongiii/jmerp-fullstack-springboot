package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;

public interface HR_workRepository extends JpaRepository<HR_work, Integer> {
    Optional<HR_work> findByEmployeeIdAndWorkDate(HR_mem employee, LocalDate workDate);
}


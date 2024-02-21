package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;

public interface HR_workRepository extends JpaRepository<HR_work, Integer> {
    Optional<HR_work> findByEmployeeIdAndWorkDate(HR_mem employee, LocalDate today);

 // 월별 조회를 위한 메서드
    Page<HR_work> findByWorkDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    
    // 사원별 조회를 위한 메서드
    @Query("SELECT w FROM HR_work w WHERE w.employeeId.employeeId = :employeeId")
    Page<HR_work> findByEmployeeIdCustom(@Param("employeeId") String employeeId, Pageable pageable);


}


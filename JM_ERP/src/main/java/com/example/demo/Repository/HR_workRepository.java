package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.HR_work;

public interface HR_workRepository extends JpaRepository<HR_work, Integer> {
	
	// 기존정보 있는지 찾는 메서드 (현재는 사용 X)
	Optional<HR_work> findByEmployeeIdAndWorkDate(HR_mem employee, LocalDate today);

	// 월별 조회를 위한 메서드
	Page<HR_work> findByWorkDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	// 사원별 조회를 위한 메서드
	@Query("SELECT w FROM HR_work w WHERE w.employeeId.employeeId = :employeeId")
	Page<HR_work> findByEmployeeIdCustom(@Param("employeeId") String employeeId, Pageable pageable);

	// 출퇴근 등록 또는 휴가 등록 시, 기존정보가 있을 경우 삭제
	@Transactional
    @Modifying
    @Query("DELETE FROM HR_work w WHERE w.employeeId = :employee AND w.workDate = :today")
	void deleteByEmployeeIdAndToday(@Param("employee") HR_mem employee, @Param("today") LocalDate today);

}

package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.HR_dept;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_mem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HR_memRepository extends JpaRepository<HR_mem, String>, JpaSpecificationExecutor<HR_mem> {

    // 1
    @Modifying        // @Query 애너테이션 : oldDeptName에 속해있는 모든 사원의 부서를 newDeptName으로 변경하는 쿼리문을 직접 실행
    @Query("UPDATE HR_mem mem SET mem.deptName = :newDeptName WHERE mem.deptName = :oldDeptName")
    void updateDeptName(@Param("newDept") HR_dept newDept, @Param("oldDept") HR_dept oldDept);

    // 2
    List<HR_mem> findByDeptName(HR_dept Dept);

    // 3
    HR_mem findByName(String name);

}

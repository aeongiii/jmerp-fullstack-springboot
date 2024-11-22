package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.HR_dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HR_deptRepository extends JpaRepository<HR_dept, String> {
    HR_dept findByDeptName(String deptName);

    HR_dept findByDeptCode(String deptCode);

}

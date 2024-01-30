package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.HR_dept;

public interface HR_deptRepository extends JpaRepository<HR_dept, String>{
	HR_dept findByDeptName(String deptName);

	HR_dept findByDeptCode(String deptCode);
}

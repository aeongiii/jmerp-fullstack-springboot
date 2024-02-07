package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.HR_doc;

public interface HR_docRepository extends JpaRepository<HR_doc, String>{

	// HR_doc에서 docDate의 연도가 :year(현재 연도)와 일치하는 행의 개수를 숫자로 반환 (= 2024년의 발행개수가 몇개인가.)
	// FUNCTION('YEAR', d.docDate) = 각 행(d)의 docDate에서 연도 부분('YEAR')을 추출하는 함수
	@Query("SELECT COUNT(d) FROM HR_doc d WHERE FUNCTION('YEAR', d.docDate) = :year")
	int countByyear(int year);

}
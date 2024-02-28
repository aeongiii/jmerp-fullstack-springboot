package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "SD_Member")		// 테이블명 지정
public class SD_Member {

    @Id
    private String memberId;	// 기본키 : 고객 아이디

    private String memberName;	// 고객이름

    private String memberPhoneNumber;	// 고객전화번호

    private String memberEmail;	// 고객이메일

    private String memberAddress;	// 고객주소

    private LocalDate memberJoinDate;	// 고객가입날짜

    private String memberCardNumber;	// 카드번호

    @Override
    public String toString() {
        return memberId;
    }

}

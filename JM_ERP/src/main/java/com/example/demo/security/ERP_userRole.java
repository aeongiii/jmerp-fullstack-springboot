package com.example.demo.security;

import lombok.Getter;

@Getter
public enum ERP_userRole {

		관리자("ROLE_관리자"),
		구매("ROLE_구매"),
	    영업("ROLE_영업"),
	    회계("ROLE_회계"),
	    인사("ROLE_인사"),
	    재고("ROLE_재고"),
	    생산("ROLE_생산"),

	    팀장("2"),
	    사원("1");
	
	
	    ERP_userRole(String value) {
	        this.value = value;
	    }

	    private String value;
	}

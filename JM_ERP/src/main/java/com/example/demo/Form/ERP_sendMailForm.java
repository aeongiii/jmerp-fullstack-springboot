package com.example.demo.Form;

import java.time.LocalDateTime;




import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ERP_sendMailForm {


	private Long num;
	
	private String reciveUser;
	
	
	private String subject;
	 
	private String content;
	
	private String image; 
	
	private String mediaFile; 
	
	private boolean checkStatus;
	
	private LocalDateTime sendTransaction;
}

package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

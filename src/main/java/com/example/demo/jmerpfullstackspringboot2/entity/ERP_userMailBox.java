package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ERP_userMailBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private ERP_user reciveUser;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private ERP_user sendUser;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String image;

    private String mediaFile;

    private boolean checkStatus;

    private LocalDateTime sendTransaction;
}


package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PC_OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;

    @Column(name = "contact_person", length = 100)
    private String contactPerson;

    @Column(name = "item", nullable = false, length = 200)
    private String item;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "completion_status", length = 50)
    private String completionStatus;

    @Column(name = "progress_status", length = 50)
    private String progressStatus;

    @Column(name = "project_name", length = 100)
    private String projectName;

    // 기본 생성자, getter, setter는 Lombok 어노테이션을 통해 자동 생성됩니다.
}

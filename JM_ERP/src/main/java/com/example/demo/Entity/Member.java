package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @Column(name = "membership")
    private boolean membership;
    
    @Column(name = "credit_date")
    private LocalDate credit_date;


    
}

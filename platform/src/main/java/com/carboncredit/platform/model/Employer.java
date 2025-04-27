package com.carboncredit.platform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Employer")
public class Employer {

    @Id
    @Column(name = "EmployerID")
    private String employerId;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "EmployerName")
    private String employerName;

    @Column(name = "active")
    private int active = 0;

    @Column(name = "name")
    private String name;

    @Column(name = "registrationNumber")
    private String registrationNumber;

    @Column(name = "AssociatedBank")
    private String associatedBank;
}

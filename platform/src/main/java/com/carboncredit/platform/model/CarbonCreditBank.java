package com.carboncredit.platform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CarbonCreditBank")
public class CarbonCreditBank {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "active")
    private Integer active;

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "licenseNumber")
    private String licenseNumber;

    @Column(name = "bankID")
    private String bankID;
}

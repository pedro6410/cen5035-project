package com.carboncredit.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Bank")
public class Bank {

    @Id
    @Column(name = "BankID")
    private String bankId;

    @Column(name = "BankName")
    private String bankName;

    @Column(name = "UserID")
    private String userId;
}
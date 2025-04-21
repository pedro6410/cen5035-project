package com.carboncredit.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "EmployeeID")
    private String employeeId;

    @Column(name = "EmployerID")
    private String employerId;

    @Column(name = "EnrollDate")
    private String enrollDate;

    @Column(name = "LastLogin")
    private String lastLogin;
}

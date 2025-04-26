package com.carboncredit.platform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "EmployeeID")
    private String employeeId;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "DepartmentID")
    private String departmentId;

    @Column(name = "EmployerID")
    private String employerId;
}

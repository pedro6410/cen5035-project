package com.carboncredit.platform.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Trip")
@Access(AccessType.FIELD)
public class Trip {

    @Id
    @Column(name = "TripID")
    private String tripId;

    @Column(name = "TripDate")
    private LocalDate tripDate;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "StartLocation")
    private String startLocation;

    @Column(name = "EndLocation")
    private String endLocation;

    @Column(name = "Method")
    private String method;

    @Column(name = "DistanceMiles")
    private BigDecimal distanceMiles;

    @Column(name = "EmployeeID")
    private String employeeId;

    @Column(name = "EmployerID")
    private String employerId;

    @Column(name = "TripCarbonCredits")
    private Float tripCarbonCredits;




}

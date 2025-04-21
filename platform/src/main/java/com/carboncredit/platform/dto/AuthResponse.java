package com.carboncredit.platform.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {



    @JsonProperty("employeeid")
    private String employeeId;

    @JsonProperty("employerid")
    private String employerId;

}

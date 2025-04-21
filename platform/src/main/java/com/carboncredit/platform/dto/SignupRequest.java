package com.carboncredit.platform.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupRequest {
    private Payload payload;

    @Getter
    @Setter
    public static class Payload {
        private String user_id;
        private String employee_id;
        private String employer_id;
        private String user_password;
    }
}
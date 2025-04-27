package com.carboncredit.platform.config;

import com.carboncredit.platform.dto.AuthResponse;
import com.carboncredit.platform.model.Administrator;
import com.carboncredit.platform.service.AdministratorService;
import com.carboncredit.platform.service.CarbonCreditBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CarbonCreditBankService carbonCreditBankService;


    private final RestTemplate restTemplate;

    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean authenticate(String id, String password, HttpSession session) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("user_id", id);
        payload.put("user_password", password);

        Map<String, Object> request = new HashMap<>();
        request.put("payload", payload);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        String lambdaUrl = "https://h2oetj3vmokksdrajr6fzrc5na0uauyy.lambda-url.us-east-1.on.aws/";

        try {

            ResponseEntity<AuthResponse> response = restTemplate.exchange(
                    lambdaUrl,
                    HttpMethod.POST,
                    entity,
                    AuthResponse.class
            );

            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Status code value: " + response.getStatusCodeValue());

            AuthResponse auth = response.getBody();

            System.out.println("auth " + auth.toString());
            System.out.println("employeeID " +auth.getEmployeeId() );
            System.out.println("employerID " +auth.getEmployerId() );
            if (auth == null){
                   // || auth.getEmployeeId() == null || auth.getEmployeeId().isEmpty()
                    //|| auth.getEmployerId() == null || auth.getEmployerId().isEmpty()) {

                System.out.println(" Login failed: Missing employeeId or employerId");
                return false;
            }
            if ((auth.getEmployeeId() == null || auth.getEmployeeId().trim().isEmpty()) &&
                    (auth.getEmployerId() == null || auth.getEmployerId().trim().isEmpty())) {

                String userId = id;

//                Administrator admin = administratorService.getAdministratorByUserId(userId);
//                System.out.println("*** Admin ID: " + admin.getAdministratorId());
//                session.setAttribute("administratorid", admin.getAdministratorId());

                Administrator admin = administratorService.getAdministratorByUserId(userId);

                if (admin != null) {
                    System.out.println("*** Admin ID: " + admin.getAdministratorId());
                    session.setAttribute("administratorid", admin.getAdministratorId());
                } else {
                    // ✅ Not Admin? Then check if CarbonCreditBank user
                    boolean isCarbonBankUser = carbonCreditBankService.existsById(userId);

                    if (isCarbonBankUser) {
                        session.setAttribute("carboncreditbankid", userId);
                        System.out.println("*** CarbonCreditBank ID: " + userId);
                    } else {
                        System.out.println("*** Neither Admin nor Carbon Bank user");
                    }
                }


            }




            session.setAttribute("userId", id);
            session.setAttribute("employeeId", auth.getEmployeeId());
            session.setAttribute("employerId", auth.getEmployerId());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

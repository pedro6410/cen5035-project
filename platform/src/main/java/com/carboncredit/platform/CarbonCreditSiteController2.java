package com.carboncredit.platform;




import com.carboncredit.platform.config.LoginService;
import com.carboncredit.platform.dto.SignupRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class CarbonCreditSiteController2 {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String id,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {

        boolean isAuthenticated = loginService.authenticate(id, password, session);

        if (isAuthenticated) {
            System.out.println("Login success");
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String employeeId = (String) session.getAttribute("employeeId");
        String employerId = (String) session.getAttribute("employerId");

        System.out.println("userId from dashboard" + userId );
        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("userId", userId);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employerId", employerId);
        return "dashboard";
    }

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SIGNUP_LAMBDA_URL = "https://hlm2roliwdnhzhyguhpmwqxdee0gsuyv.lambda-url.us-east-1.on.aws/";

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("userId") String userId,
                                @RequestParam("password") String password,
                                @RequestParam("role") String role,
                                @RequestParam(value = "employeeId", required = false) String employeeId,
                                @RequestParam(value = "employerId", required = false) String employerId,
                                Model model) {


        SignupRequest request = new SignupRequest();
        SignupRequest.Payload payload = new SignupRequest.Payload();
        payload.setUser_id(userId);
        payload.setUser_password(password);

        if ("employee".equalsIgnoreCase(role)) {
            payload.setEmployee_id(employeeId);
            payload.setEmployer_id(employerId);
        }
        if ("employer".equalsIgnoreCase(role)) {
            payload.setEmployer_id(employerId);
        }

        request.setPayload(payload);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SignupRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    SIGNUP_LAMBDA_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                model.addAttribute("message", "User created successfully!");
                return "signup-success";
            } else {
                model.addAttribute("error", "Failed to create user.");
                return "signup";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error occurred: " + e.getMessage());
            return "signup";
        }
    }
}

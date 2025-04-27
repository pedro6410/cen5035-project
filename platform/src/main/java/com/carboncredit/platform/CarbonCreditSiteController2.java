package com.carboncredit.platform;




import com.carboncredit.platform.config.LoginService;
import com.carboncredit.platform.dto.SignupRequest;
import com.carboncredit.platform.model.CarbonCreditBank;
import com.carboncredit.platform.model.Employee;
import com.carboncredit.platform.model.Employer;
import com.carboncredit.platform.service.CarbonCreditBankService;
import com.carboncredit.platform.service.EmployeeService;
import com.carboncredit.platform.service.EmployerService;
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

    @Autowired
    private EmployerService employerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CarbonCreditBankService bankService;


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
//
//        if (isAuthenticated) {
//            System.out.println("Login success");
//            return "redirect:/dashboard";
//        } else {
//            model.addAttribute("error", "Invalid credentials");
//            return "login";
//        }

        if (isAuthenticated) {
            System.out.println("****** Login success");

            if (session.getAttribute("carboncreditbankid") != null) {
                // ✅ If carboncreditbankid present in session, user is a bank user
                return "redirect:/carbon-bank-trips";
            } else {
                // ✅ Else normal dashboard
                return "redirect:/dashboard";
            }

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

    @GetMapping("/create-user")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("userId") String userId,
                                @RequestParam("password") String password,
                                @RequestParam("role") String role,
                                @RequestParam(value = "employeeId", required = false) String employeeId,
                                @RequestParam(value = "employerId", required = false) String employerId,
                                @RequestParam(value = "employerName", required = false) String employerName,
                                @RequestParam(value = "registrationNumber", required = false) String registrationNumber,
                                @RequestParam(value = "associatedBank", required = false) String associatedBank,
                                @RequestParam(value = "employeeName", required = false) String employeeName,
                                @RequestParam(value = "departmentId", required = false) String departmentId,
                                @RequestParam(value = "bankName", required = false) String bankName,
                                @RequestParam(value = "licenseNumber", required = false) String licenseNumber,
                                @RequestParam(value = "bankActive", required = false) Integer bankActive,
                                @RequestParam(value = "bankID", required = false) String bankID,
                                Model model){

        System.out.println("Signup triggered for role " + role );
        System.out.println("employeeId: " + employeeId);
        System.out.println("employerId: " + employerId);
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

                if ("employer".equals(role)) {
                    Employer employer = new Employer();
                    employer.setEmployerId(employerId);
                    employer.setUserId(userId);
                    employer.setEmployerName(employerName);
                    employer.setRegistrationNumber(registrationNumber);
                    employer.setAssociatedBank(associatedBank);
                    employer.setActive(0); // Optional

                    employerService.saveEmployer(employer);
                }

                if ("employee".equals(role)) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId);
                    employee.setUserId(userId);
                    employee.setEmployeeName(employeeName);
                    employee.setDepartmentId(departmentId);
                    employee.setEmployerId(employerId);

                    employeeService.saveEmployee(employee);
                }

                if ("bank".equals(role)) {
                    System.out.println("Bank ID" + userId);
                    CarbonCreditBank bank = new CarbonCreditBank();
                    bank.setId(userId);
                    bank.setBankName(bankName);
                    bank.setLicenseNumber(licenseNumber);
                    bank.setActive(bankActive != null ? bankActive : 0);
                    bank.setBankID(bankID);
                    bankService.save(bank);
                }

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
    @GetMapping("/create-employee")
    public String createEmployeePage(HttpSession session, Model model) {
        // Pass employerId from session to signup page if needed
        String employerId = (String) session.getAttribute("employerId");
        model.addAttribute("employerId", employerId);
        model.addAttribute("autoSelectRole", "employee");

        return "signup";  // Reuse your signup.jsp
    }

}

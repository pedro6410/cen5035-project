package com.carboncredit.platform;

import com.carboncredit.platform.dto.UserWithRole;
import com.carboncredit.platform.model.*;
import com.carboncredit.platform.repo.TripRepository;
import com.carboncredit.platform.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import com.carboncredit.platform.model.CarbonCreditBank;


@Controller
public class CarbonCreditSiteController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;


    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private CarbonCreditBankService carbonCreditBankService;




    @GetMapping("/")
    public String root(HttpSession session) {
        if (session != null && session.getAttribute("userId") != null) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }



    @RequestMapping("*")
    public String handleUnmappedPaths() {
        return "dashboard";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<UserWithRole> usersWithRoles = userService.getAllUsersWithRoles();
        model.addAttribute("users", usersWithRoles);
        return "user-list";
    }

    @GetMapping("/employer-dashboard")
    public String showEmployerDashboard(HttpSession session, Model model) {
        String employerId = (String) session.getAttribute("employerId");

        if (employerId != null) {
            Employer employer = employerService.getEmployerById(employerId);
            if (employer != null) {
                model.addAttribute("employerName", employer.getEmployerName());
            } else {
                model.addAttribute("employerName", "Employer");
            }
        } else {
            model.addAttribute("employerName", "Employer");
        }

        return "employer-dashboard";
    }

    @GetMapping("/employee-list")
    public String showEmployeeList(Model model, HttpSession session) {
        String employerId = (String) session.getAttribute("employerId");
        if (employerId != null) {
            List<Employee> employees = employeeService.getEmployeesByEmployerId(employerId);
            model.addAttribute("employees", employees);
        }
        if (employerId != null) {
            Employer employer = employerService.getEmployerById(employerId);
            if (employer != null) {
                model.addAttribute("employerName", employer.getEmployerName());
            } else {
                model.addAttribute("employerName", "Employer");
            }
        } else {
            model.addAttribute("employerName", "Employer");
        }

        return "employee-list";
    }

    @GetMapping("/employee-dashboard")
    public String showEmployeeDashboard(HttpSession session, Model model) {
        String employeeId = (String) session.getAttribute("employeeId");

        if (employeeId == null) {
            return "redirect:/login";
        }

        List<Trip> trips = tripRepository.findByEmployeeId(employeeId);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("trips", trips);
        if (employeeId != null) {
            Employee employee = employeeService.getEmployeeByUserId(employeeId);
            if (employee != null) {
                model.addAttribute("employeeName", employee.getEmployeeName());
            } else {
                model.addAttribute("employeeName", "Employee");
            }
        } else {
            model.addAttribute("employeeName", "Employee");
        }

        return "employee-dashboard";
    }


    @PostMapping("/trips")
    public String getTripsByEmployer(@RequestParam("employerId") String employerId, Model model) {
        List<Trip> trips = tripRepository.findByEmployerId(employerId);
        float totalCredits = tripService.calculateTotalCarbonCredits(trips);

        model.addAttribute("trips", trips);
        model.addAttribute("employerId", employerId);
        model.addAttribute("totalCredits", totalCredits);

        System.out.println("employerId" + employerId);

        if (employerId != null) {
            Employer employer = employerService.getEmployerById(employerId);
            if (employer != null) {
                model.addAttribute("employerName", employer.getEmployerName());
            } else {
                model.addAttribute("employerName", "Employer");
            }
        } else {
            model.addAttribute("employerName", "Employer");
        }

        return "trip-list";
    }
    @GetMapping("/trips")
    public String getTripsByEmployerFromSession(HttpSession session, Model model) {
        String employerId = (String) session.getAttribute("employerId");

        if (employerId == null) {
            return "redirect:/login";
        }

        List<Trip> trips = tripRepository.findByEmployerId(employerId);
        float totalCredits = tripService.calculateTotalCarbonCredits(trips);

        model.addAttribute("trips", trips);
        model.addAttribute("employerId", employerId);
        model.addAttribute("totalCredits", totalCredits);

        if (employerId != null) {
            Employer employer = employerService.getEmployerById(employerId);
            if (employer != null) {
                model.addAttribute("employerName", employer.getEmployerName());
            } else {
                model.addAttribute("employerName", "Employer");
            }
        } else {
            model.addAttribute("employerName", "Employer");
        }

        return "trip-list";
    }



    @GetMapping("/sell")
    public String showSellPage(@RequestParam("employerId") String employerId, Model model) {
        List<CarbonCreditBank> banks = carbonCreditBankService.getAllBanks();
        model.addAttribute("banks", banks);
        model.addAttribute("employerId", employerId);
        return "sell";
    }

    @PostMapping("/submit-sell")
    public String submitSell(@RequestParam("bankId") String bankId,
                             @RequestParam("amount") float amount,
                             @RequestParam("employerId") String employerId,
                             Model model) {

        Trip dummyTrip = new Trip();
        dummyTrip.setTripId(tripService.generateDummyTripId());

        LocalDateTime nowUtc = LocalDateTime.now(ZoneOffset.UTC);
        dummyTrip.setTripDate(nowUtc.toLocalDate());
        dummyTrip.setStartTime(nowUtc);
        dummyTrip.setEndTime(nowUtc);
        dummyTrip.setTripCarbonCredits(-Math.abs(amount));
        dummyTrip.setEmployerId(employerId);

        tripRepository.save(dummyTrip);

        model.addAttribute("message", "Credits sold and recorded as: " + dummyTrip.getTripId());
        return "sell-success";
    }


    @GetMapping("/buy")
    public String showBuyPage(@RequestParam("employerId") String employerId, Model model) {
        List<CarbonCreditBank> banks = carbonCreditBankService.getAllBanks();
        model.addAttribute("banks", banks);
        model.addAttribute("employerId", employerId);
        return "buy";
    }

    @PostMapping("/submit-buy")
    public String submitBuy(@RequestParam("employerId") String employerId,
                            @RequestParam("amount") float amount,
                            Model model) {

        Trip dummyTrip = new Trip();

        dummyTrip.setTripId(tripService.generateDummyTripId());

        LocalDateTime nowUtc = LocalDateTime.now(ZoneOffset.UTC);
        dummyTrip.setTripDate(nowUtc.toLocalDate());
        dummyTrip.setStartTime(nowUtc);
        dummyTrip.setEndTime(nowUtc);
        dummyTrip.setTripCarbonCredits(Math.abs(amount));
        dummyTrip.setEmployerId(employerId);

        tripRepository.save(dummyTrip);

        model.addAttribute("message", "Credits bought and recorded as: " + dummyTrip.getTripId());
        return "buy-success";
    }

    @GetMapping("/carbon-bank-trips")
    public String showTripsForCarbonBank(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        System.out.println("Bank user logged in using " + userId );
        CarbonCreditBank bank = carbonCreditBankService.getBankById(userId);


        List<Trip> trips = tripService.getTripsForCarbonCreditBank(userId);

        model.addAttribute("trips", trips);
        model.addAttribute("bankName", bank.getBankName());

        return "carbon-bank-trips";
    }
}

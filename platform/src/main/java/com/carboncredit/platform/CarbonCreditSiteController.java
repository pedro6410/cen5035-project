package com.carboncredit.platform;

import com.carboncredit.platform.model.Bank;
import com.carboncredit.platform.model.Trip;
import com.carboncredit.platform.repo.TripRepository;
import com.carboncredit.platform.service.BankService;
import com.carboncredit.platform.service.TripService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
public class CarbonCreditSiteController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    private BankService bankService;



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


    @GetMapping("/employee-dashboard")
    public String showEmployeeDashboard(HttpSession session, Model model) {
        String employeeId = (String) session.getAttribute("employeeId");

        if (employeeId == null) {
            return "redirect:/login";
        }

        List<Trip> trips = tripRepository.findByEmployeeId(employeeId);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("trips", trips);

        return "employee-dashboard";
    }


    @PostMapping("/trips")
    public String getTripsByEmployer(@RequestParam("employerId") String employerId, Model model) {
        List<Trip> trips = tripRepository.findByEmployerId(employerId);
        float totalCredits = tripService.calculateTotalCarbonCredits(trips);

        model.addAttribute("trips", trips);
        model.addAttribute("employerId", employerId);
        model.addAttribute("totalCredits", totalCredits);

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

        return "trip-list";
    }



    @GetMapping("/sell")
    public String showSellPage(@RequestParam("employerId") String employerId, Model model) {
        List<Bank> banks = bankService.getAllBanks();
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
        List<Bank> banks = bankService.getAllBanks();
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
}

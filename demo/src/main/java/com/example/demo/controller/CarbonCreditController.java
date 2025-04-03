package com.example.demo.controller;
import com.example.demo.model.CarbonCredit;
import com.example.demo.service.CarbonCreditService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credits")
public class CarbonCreditController {
    private final CarbonCreditService service;

    public CarbonCreditController(CarbonCreditService service) {
        this.service = service;
    }
    @GetMapping("/dashboard")
    public List<CarbonCredit> getDashboardCredits() {
        return service.getCreditsForLast7Years();
    }
}

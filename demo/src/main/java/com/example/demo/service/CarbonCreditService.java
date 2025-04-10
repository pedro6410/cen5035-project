package com.example.demo.service;

import com.example.demo.model.CarbonCredit;
import com.example.demo.repository.CarbonCreditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarbonCreditService {

    private final CarbonCreditRepository repository;

    //  Constructor (injects the repository)
    public CarbonCreditService(CarbonCreditRepository repository) {
        this.repository = repository;
    }

    //  Actual method to get data for the dashboard
    public List<CarbonCredit> getCreditsForLast7Years() {
        LocalDate sevenYearsAgo = LocalDate.now().minusYears(7);
        return repository.findAllByDateAfter(sevenYearsAgo);
    }
}

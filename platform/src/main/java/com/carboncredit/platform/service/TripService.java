package com.carboncredit.platform.service;

import com.carboncredit.platform.model.Employer;
import com.carboncredit.platform.repo.CarbonCreditBankRepository;
import com.carboncredit.platform.repo.EmployerRepository;
import com.carboncredit.platform.repo.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.carboncredit.platform.model.Trip;

@Service
public class TripService {

    @Autowired
    private CarbonCreditBankRepository carbonCreditBankRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private TripRepository tripRepository;

    public float calculateTotalCarbonCredits(List<Trip> trips) {
        float total = 0f;
        for (Trip trip : trips) {
            if (trip.getTripCarbonCredits() != null) {
                total += trip.getTripCarbonCredits();
            }
        }
        return total;
    }

    public String generateDummyTripId (){
        int tripID6Digit = (int)(Math.random() * 1_000_000 + 100_000);
        int tripIDOverflow = tripID6Digit - 1_000_000;
        if (tripIDOverflow > 0) {
            tripID6Digit = tripID6Digit - tripIDOverflow;
        }
        return ("TRIP" + tripID6Digit);
    }

    public List<Trip> getTripsForCarbonCreditBank(String userId) {
        var bankOptional = carbonCreditBankRepository.findById(userId);
        if (bankOptional.isEmpty()) {
            return new ArrayList<>();
        }
        String bankID = bankOptional.get().getBankID();
        System.out.println("*** Found BankID: " + bankID);

        List<Employer> employers = employerRepository.findByAssociatedBank(bankID);
        if (employers.isEmpty()) {
            System.out.println("*** No employers found under BankID: " + bankID);
            return new ArrayList<>();
        }

        List<String> employerIds = new ArrayList<>();
        for (Employer employer : employers) {
            employerIds.add(employer.getEmployerId());
            System.out.println("*** Found Employer: " + employer.getEmployerId());
        }


        List<Trip> filteredTrips = tripRepository.findByEmployerIdIn(employerIds);

        System.out.println("*** Total Trips Found: " + filteredTrips.size());
        return filteredTrips;
    }



}
package com.carboncredit.platform.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.carboncredit.platform.model.Trip;

@Service
public class TripService {

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

}
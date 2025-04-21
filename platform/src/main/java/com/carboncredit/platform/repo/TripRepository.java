package com.carboncredit.platform.repo;



import com.carboncredit.platform.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {
    List<Trip> findByEmployerId(String employerId);

    List<Trip> findByEmployeeId(String employeeId);
}

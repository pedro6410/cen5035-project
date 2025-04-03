package com.example.demo.repository;
import com.example.demo.model.CarbonCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
public interface CarbonCreditRepository extends JpaRepository<CarbonCredit, Long> {
    List<CarbonCredit> findAllByDateAfter(LocalDate startDate);
}

package com.example.demo.repository;

import com.example.demo.model.CarbonCreditBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarbonCreditBankRepository extends JpaRepository<CarbonCreditBank, Long> {
}


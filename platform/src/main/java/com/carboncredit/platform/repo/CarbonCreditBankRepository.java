package com.carboncredit.platform.repo;

import com.carboncredit.platform.model.CarbonCreditBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarbonCreditBankRepository extends JpaRepository<CarbonCreditBank, String> {

}
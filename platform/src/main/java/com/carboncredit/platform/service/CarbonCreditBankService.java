package com.carboncredit.platform.service;

import com.carboncredit.platform.model.CarbonCreditBank;
import com.carboncredit.platform.repo.CarbonCreditBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarbonCreditBankService {

    @Autowired
    private CarbonCreditBankRepository repository;

    public CarbonCreditBank save(CarbonCreditBank bank) {
        return repository.save(bank);
    }

    public List<CarbonCreditBank> getAllBanks() {
        return repository.findAll();
    }
}

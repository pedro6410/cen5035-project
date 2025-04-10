package com.example.demo.service;

import com.example.demo.model.CarbonCreditBank;
import com.example.demo.repository.CarbonCreditBankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarbonCreditBankService {

    private final CarbonCreditBankRepository repository;

    public CarbonCreditBankService(CarbonCreditBankRepository repository) {
        this.repository = repository;
    }

    public List<CarbonCreditBank> getAllBanks() {
        return repository.findAll();
    }

    public CarbonCreditBank registerBank(CarbonCreditBank bank) {
        return repository.save(bank);
    }

    public void deregisterBank(Long id) {
        repository.deleteById(id);
    }

    public Optional<CarbonCreditBank> getBankById(Long id) {
        return repository.findById(id);
    }
}

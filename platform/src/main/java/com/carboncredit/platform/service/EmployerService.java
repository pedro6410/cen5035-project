package com.carboncredit.platform.service;


import com.carboncredit.platform.model.Employer;
import com.carboncredit.platform.repo.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public boolean existsByEmployerId(String employerId) {
        return employerRepository.existsByEmployerId(employerId);
    }

    public Employer getEmployerById(String employerId) {
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        return optionalEmployer.orElse(null);
    }
}

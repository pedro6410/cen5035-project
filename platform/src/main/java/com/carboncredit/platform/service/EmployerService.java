package com.carboncredit.platform.service;


import com.carboncredit.platform.model.Employer;
import com.carboncredit.platform.repo.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

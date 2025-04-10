package com.example.demo.service;

import com.example.demo.model.Employer;
import com.example.demo.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    private final EmployerRepository repository;

    public EmployerService(EmployerRepository repository) {
        this.repository = repository;
    }

    //  Save/register a new employer
    public Employer registerEmployer(Employer employer) {
        return repository.save(employer);
    }

    // Get all employers
    public List<Employer> getAllEmployers() {
        return repository.findAll();
    }

    // Delete employer by ID
    public void deleteEmployer(Long id) {
        repository.deleteById(id);
    }
}

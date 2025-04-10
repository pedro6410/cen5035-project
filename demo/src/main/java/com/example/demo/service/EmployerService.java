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

    public Employer register(Employer employer) {
        employer.setActive(true);
        return repository.save(employer);
    }

    public void deregister(Long id) {
        repository.findById(id).ifPresent(emp -> {
            emp.setActive(false);
            repository.save(emp);
        });
    }

    public List<Employer> getAll() {
        return repository.findAll();
    }
}

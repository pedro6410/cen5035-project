package com.example.demo.controller;

import com.example.demo.model.Employer;
import com.example.demo.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    // POST: Register new employer
    @PostMapping
    public Employer registerEmployer(@RequestBody Employer employer) {
        return employerService.registerEmployer(employer);
    }

    // GET: All employers
    @GetMapping
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    //  DELETE: Deregister employer
    @DeleteMapping("/{id}")
    public void deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
    }
}

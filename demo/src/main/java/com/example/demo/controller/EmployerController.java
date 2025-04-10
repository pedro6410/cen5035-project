package com.example.demo.controller;

import com.example.demo.model.Employer;
import com.example.demo.service.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {
    private final EmployerService service;

    public EmployerController(EmployerService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Employer register(@RequestBody Employer employer) {
        return service.register(employer);
    }

    @DeleteMapping("/deregister/{id}")
    public void deregister(@PathVariable Long id) {
        service.deregister(id);
    }

    @GetMapping
    public List<Employer> getAll() {
        return service.getAll();
    }
}

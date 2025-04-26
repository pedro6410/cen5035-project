package com.carboncredit.platform.service;

import com.carboncredit.platform.model.Employee;
import com.carboncredit.platform.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean existsByEmployeeId(String employeeId) {
        return employeeRepository.existsByEmployeeId(employeeId);
    }

    public List<Employee> getEmployeesByEmployerId(String employerId) {
        return employeeRepository.findByEmployerId(employerId);
    }
}

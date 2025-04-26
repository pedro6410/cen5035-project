package com.carboncredit.platform.service;

import com.carboncredit.platform.dto.UserWithRole;
import com.carboncredit.platform.model.Employee;
import com.carboncredit.platform.model.Employer;
import com.carboncredit.platform.model.CarbonCreditBank;
import com.carboncredit.platform.model.User;
import com.carboncredit.platform.repo.AdministratorRepository;
import com.carboncredit.platform.repo.EmployeeRepository;
import com.carboncredit.platform.repo.EmployerRepository;
import com.carboncredit.platform.repo.UserRepository;
import com.carboncredit.platform.repo.CarbonCreditBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private CarbonCreditBankRepository carbonCreditBankRepository;

    public List<UserWithRole> getAllUsersWithRoles() {
        return userRepository.findAll().stream()
                .filter(user -> !administratorRepository.existsByUserId(user.getId()))  // Skip Administrators
                .map(user -> {
                    String role;
                    String userName = "";

                    if (user.getEmployeeId() != null && user.getEmployerId() != null) {
                        role = "Employee";
                        Employee employee = employeeRepository.findById(user.getEmployeeId()).orElse(null);
                        if (employee != null) {
                            userName = employee.getEmployeeName();
                        }
                    } else if (user.getEmployerId() != null) {
                        role = "Employer";
                        Employer employer = employerRepository.findById(user.getEmployerId()).orElse(null);
                        if (employer != null) {
                            userName = employer.getEmployerName();
                        }
                    } else {
                        role = "CarbonCreditBank";
                        CarbonCreditBank bank = carbonCreditBankRepository.findById(user.getId()).orElse(null);
                        if (bank != null) {
                            userName = bank.getBankName();
                        }
                    }

                    return new UserWithRole(user, role, userName);
                })
                .collect(Collectors.toList());
    }
}

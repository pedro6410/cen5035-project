package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByEmployeeId(String employeeId);

    List<Employee> findByEmployerId(String employerId);
}

package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, String> {
    boolean existsByEmployerId(String employerId);
}

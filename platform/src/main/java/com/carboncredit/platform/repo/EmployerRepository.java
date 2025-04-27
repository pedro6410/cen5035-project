package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, String> {
    boolean existsByEmployerId(String employerId);
    List<Employer> findByAssociatedBank(String associatedBank);
}

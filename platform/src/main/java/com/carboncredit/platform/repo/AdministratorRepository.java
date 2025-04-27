package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    Administrator findByUserId(String userId);

    boolean existsByUserId(String userId);


}

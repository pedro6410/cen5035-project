package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndPasswordHash(String id, String passwordHash);
}

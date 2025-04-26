package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}


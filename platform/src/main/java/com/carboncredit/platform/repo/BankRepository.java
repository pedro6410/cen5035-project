package com.carboncredit.platform.repo;


import com.carboncredit.platform.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {
}

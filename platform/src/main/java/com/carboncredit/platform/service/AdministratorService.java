package com.carboncredit.platform.service;


import com.carboncredit.platform.model.Administrator;
import com.carboncredit.platform.repo.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator getAdministratorByUserId(String userId) {
        return administratorRepository.findByUserId(userId);
    }


}

package com.example.bookstore.services;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.Admin;
import com.example.bookstore.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordConfig passwordConfig;
    public void saveAdmin(Admin admin){
        admin.setPassword(passwordConfig.passwordEncoder().encode(admin.getPassword()));
        adminRepository.save(admin);
    }



}

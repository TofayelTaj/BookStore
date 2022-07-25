package com.example.bookstore.security;

import com.example.bookstore.entities.Admin;
import com.example.bookstore.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Admin admin = adminRepository.findAdminByEmail(username);

       if(admin == null){
           throw new UsernameNotFoundException("User Not Found");
       }
       AdminDetails adminDetails = new AdminDetails(admin);
       return adminDetails;
    }
}

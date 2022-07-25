package com.example.bookstore.security;

import com.example.bookstore.controllers.CustomerController;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(userEmail);
        CustomerDetails customerDetails = new CustomerDetails(customer);
        return customerDetails;
    }
}

package com.example.bookstore.services;

import com.example.bookstore.entities.Customer;
import com.example.bookstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

}

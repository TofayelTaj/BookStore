package com.example.bookstore.controllers;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String getCustomerRegisterPage(){
        return "customer-registration";
    }

    @PostMapping()
    public String processCustomerRegistration(@ModelAttribute Customer customer){
        customer.setPassword(passwordConfig.passwordEncoder().encode(customer.getPassword()));
        customerService.saveCustomer(customer);
        return "customer-registration";
    }

}

package com.example.bookstore.controllers;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public String processCustomerRegistration(@ModelAttribute Customer customer, HttpServletRequest request){
        customer.setPassword(passwordConfig.passwordEncoder().encode(customer.getPassword()));
        customerService.saveCustomer(customer);
        return "redirect:" + request.getHeader("referer");
    }

}

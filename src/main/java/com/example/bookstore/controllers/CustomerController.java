package com.example.bookstore.controllers;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/sign-up")
    public String getCustomerSignUpPage(Model model) {
        return "customer-signup";
    }

    @PostMapping(value = "/sign-up")
    public String processCustomerRegistration(@Valid @ModelAttribute Customer customer, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("nameError", result.getFieldErrors("name"));
            model.addAttribute("emailError", result.getFieldErrors("email"));
            model.addAttribute("passwordError", result.getFieldErrors("password"));

            return "customer-signup";
        }
        try {
            customer.setPassword(passwordConfig.passwordEncoder().encode(customer.getPassword()));
            customerService.saveCustomer(customer);
            model.addAttribute("success", "Sign Up Success....");

        } catch (Exception e) {
            model.addAttribute("error", "Email already exist !");
            return "customer-signup";
        }
        return "customer-signup";

    }

}

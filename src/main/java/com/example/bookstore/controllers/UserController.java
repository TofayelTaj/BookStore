package com.example.bookstore.controllers;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.User;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {


    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private UserService userService;

    @GetMapping("/customer/sign-up")
    public String getCustomerSignUpPage() {
        return "customer-signup";
    }

    @PostMapping(value = "/user/sign-up")
    public String processCustomerRegistration(@Valid @ModelAttribute User user, BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("nameError", result.getFieldErrors("name"));
            model.addAttribute("emailError", result.getFieldErrors("email"));
            model.addAttribute("passwordError", result.getFieldErrors("password"));

            return "customer-signup";
        }
        try {

            user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
            userService.saveUser(user);
            model.addAttribute("success", "Sign Up Success....");

        } catch (Exception e) {
            model.addAttribute("error", "Email already exist !");
            return "customer-signup";
        }
        return "customer-signup";
    }
}

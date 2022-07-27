package com.example.bookstore.controllers;


import com.example.bookstore.entities.Admin;
import com.example.bookstore.entities.Book;
import com.example.bookstore.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String adminDashboardPage(){
        return "admin-dashboard";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationPage(){
        return "admin-registration";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute Admin admin , Model model){
        adminService.saveAdmin(admin);
        model.addAttribute("saveMsg", "admin save successful");
        return "admin-registration";
    }




}

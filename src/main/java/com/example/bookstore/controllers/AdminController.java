package com.example.bookstore.controllers;


import com.example.bookstore.services.AdminService;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ModelAndView adminDashboardPage(@RequestParam String status) {
       return adminService.getAdminDashboardPage(status);
    }

    @PostMapping("/change-status")
    public ModelAndView changeOrderStatus(@RequestParam String status, @RequestParam("orderId") Long id){
       orderService.changeStatusByOrderId(status, id);
       return new ModelAndView("redirect:/admin/?status=all");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "admin-registration";
    }
}

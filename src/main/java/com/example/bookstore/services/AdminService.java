package com.example.bookstore.services;

import com.example.bookstore.configuration.PasswordConfig;
import com.example.bookstore.entities.Admin;
import com.example.bookstore.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderService orderService;

    @Autowired
    private PasswordConfig passwordConfig;

    public void saveAdmin(Admin admin) {
        admin.setPassword(passwordConfig.passwordEncoder().encode(admin.getPassword()));
        adminRepository.save(admin);
    }


    public ModelAndView getAdminDashboardPage(String status) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-dashboard");

        if (status.equals("all")) {
            modelAndView.addObject("orders", orderService.allOrders());
        } else{
            modelAndView.addObject("orders", orderService.findAllByStatus(status));
        }
        return modelAndView;
    }

}

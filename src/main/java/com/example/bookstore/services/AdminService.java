package com.example.bookstore.services;

import com.example.bookstore.configuration.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AdminService {


    @Autowired
    private OrderService orderService;

    @Autowired
    private PasswordConfig passwordConfig;




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

package com.example.bookstore.controllers;

import com.example.bookstore.entities.CartItem;
import com.example.bookstore.entities.Order;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order" )
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String doOrder(HttpServletRequest request, Order order){

        return "redirect:" + request.getHeader("referer");
    }

}

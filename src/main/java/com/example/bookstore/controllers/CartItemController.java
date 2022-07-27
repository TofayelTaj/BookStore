package com.example.bookstore.controllers;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CartItem;
import com.example.bookstore.services.CartItemService;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartItemController {


    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public String addToCart(@ModelAttribute CartItem cartItem, @ModelAttribute Book book, HttpServletRequest request){

        String name = request.getUserPrincipal().getName();
        cartItem.setCustomer(customerService.findByEmail(name));
        cartItemService.addToCart(cartItem);
        return "redirect:" + request.getHeader("referer");
    }
}

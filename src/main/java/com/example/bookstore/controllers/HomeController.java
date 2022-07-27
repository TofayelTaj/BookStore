package com.example.bookstore.controllers;

import com.example.bookstore.entities.Customer;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartItemService;
import com.example.bookstore.services.CategoryService;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String getHomePage(Model model, Principal principal){
        Customer customer = null;
        if(principal != null) {
            customer = customerService.findByEmail(principal.getName());
            model.addAttribute("cartItemCount", cartItemService.getCartItemCount(customer.getId()));
        }
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "index";
    }





}

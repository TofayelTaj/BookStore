package com.example.bookstore.controllers;

import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CategoryService;
import com.example.bookstore.services.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/")
    public ModelAndView getHomePage(ModelAndView modelAndView, Principal principal){
        modelAndView = homePageService.getIndexPage(
                            principal,
                            bookService.getAllBooks(),
                            categoryService.getAllCategory() );
        return modelAndView;
    }
}

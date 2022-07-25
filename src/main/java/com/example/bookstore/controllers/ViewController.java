package com.example.bookstore.controllers;

import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }





}

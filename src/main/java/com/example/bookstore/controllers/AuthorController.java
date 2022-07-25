package com.example.bookstore.controllers;

import com.example.bookstore.entities.Author;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public String create(@ModelAttribute Author author, HttpServletRequest request){
        authorService.saveAuthor(author);
        return "redirect:/" + request.getHeader("Referer");
    }


}

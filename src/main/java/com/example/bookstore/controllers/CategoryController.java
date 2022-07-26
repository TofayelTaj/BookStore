package com.example.bookstore.controllers;

import com.example.bookstore.entities.Category;
import com.example.bookstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String addCategoryPage(){
        return "create-category";
    }

    @PostMapping
    public String createCategory(Category category, HttpServletRequest request){
        categoryService.save(category);
        return "redirect:" + request.getHeader("referer");
    }


}

package com.example.bookstore.services;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Category;
import com.example.bookstore.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Service
public class HomePageService {

    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CategoryService categoryService;

    public ModelAndView getIndexPage( Principal principal, List<Book> books, List<Category> categories){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        Customer customer = null;
        if(principal != null) {
            customer = customerService.findByEmail(principal.getName());
            modelAndView.addObject("cartItemCount", cartItemService.getCartItemCount(customer.getId()));
        }
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}

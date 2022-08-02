package com.example.bookstore.controllers;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CartItem;
import com.example.bookstore.services.CartItemService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartItemController {


    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;


    @PostMapping
    public String addToCart(@ModelAttribute CartItem cartItem, @ModelAttribute Book book, HttpServletRequest request){

        String name = request.getUserPrincipal().getName();
        cartItem.setUser(userService.findByEmail(name));
        cartItemService.addToCart(cartItem);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId, HttpServletRequest request){
        cartItemService.removeFromCart(cartItemId);
        return "redirect:" + request.getHeader("referer");
    }


}

package com.example.bookstore.services;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CartItem;
import com.example.bookstore.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public int getCartItemCount(Long userId){
        return cartItemRepository.getCartItemCountByUserId(userId);
    }
}

package com.example.bookstore.services;

import com.example.bookstore.entities.CartItem;
import com.example.bookstore.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }


    public void formatCart(Long customerId){
        cartItemRepository.formatCartByCustomerId(customerId);
    }


    public void removeFromCart(Long CartItemId){
        cartItemRepository.deleteById(CartItemId);
    }

    public int getCartItemCount(Long userId){
        return cartItemRepository.getCartItemCountByUserId(userId);
    }

    public List<CartItem> getCartItemsByCustomerId(Long id){
       return cartItemRepository.getCartItemsByCustomerId(id);
    }

}

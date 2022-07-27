package com.example.bookstore.services;

import com.example.bookstore.entities.Order;
import com.example.bookstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

}

package com.example.bookstore.services;

import com.example.bookstore.entities.Order;
import com.example.bookstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }


    public List<Order> orderListByCustomerId(Long customerId){
       return orderRepository.findAllByCustomerId(customerId);
    }


}

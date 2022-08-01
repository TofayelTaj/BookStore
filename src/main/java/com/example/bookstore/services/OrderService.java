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

    public List<Order> findOrdersByCustomerIdAndStatus(Long customerId, String status){
        return orderRepository.findAllByCustomerIdAndStatus(customerId, status);
    }


    public List<Order> findAllByStatus(String status){
       return orderRepository.findAllByStatus(status);
    }

    public List<Order> allOrders(){
        return orderRepository.findAll();
    }

    public void changeStatusByOrderId(String status, Long id){
        orderRepository.changeStatusByOrderId(status, id);
    }

}

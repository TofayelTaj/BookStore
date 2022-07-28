package com.example.bookstore.repositories;

import com.example.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from order_table where customer_id = :customerId", nativeQuery = true)
    List<Order> findAllByCustomerId(@Param("customerId") Long customerId);
}

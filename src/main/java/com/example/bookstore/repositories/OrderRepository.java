package com.example.bookstore.repositories;

import com.example.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from order_table where customer_id = :customerId", nativeQuery = true)
    List<Order> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "select * from order_table where customer_id = :customerId and status = :status", nativeQuery = true)
    List<Order> findAllByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status);
    @Query(value = "select * from order_table where status = :status", nativeQuery = true)
    List<Order> findAllByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "update order_table set status = :status where id = :id", nativeQuery = true)
    void changeStatusByOrderId(@Param("status") String status, @Param("id") Long id);
}

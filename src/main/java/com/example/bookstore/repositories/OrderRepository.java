package com.example.bookstore.repositories;

import com.example.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = "select * from order_table where user_id = :userId", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") Long userId);

    @Query(value = "select * from order_table where user_id = :userId and status = :status", nativeQuery = true)
    List<Order> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
    @Query(value = "select * from order_table where status = :status", nativeQuery = true)
    List<Order> findAllByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "update order_table set status = :status where id = :id", nativeQuery = true)
    void changeStatusByOrderId(@Param("status") String status, @Param("id") Long id);
}

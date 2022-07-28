package com.example.bookstore.repositories;

import com.example.bookstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT count('customer_id') FROM cart_item where customer_id = :customer_id", nativeQuery = true)
    int getCartItemCountByUserId(@Param("customer_id") Long customer_id);

    @Query(value = "SELECT * FROM cart_item where customer_id = :customerId", nativeQuery = true)
    List<CartItem> getCartItemsByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Transactional
    @Query(value = "delete  FROM cart_item where customer_id = :customerId", nativeQuery = true)
    void formatCartByCustomerId(@Param("customerId") Long customerId);

}

package com.example.bookstore.repositories;

import com.example.bookstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT count('customer_id') FROM cart_item where user_id = :userId", nativeQuery = true)
    int getCartItemCountByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM cart_item where user_id = :userId", nativeQuery = true)
    List<CartItem> getCartItemsByCustomerId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete  FROM cart_item where user_id = :userId", nativeQuery = true)
    void formatCartByCustomerId(@Param("userId") Long userId);

}

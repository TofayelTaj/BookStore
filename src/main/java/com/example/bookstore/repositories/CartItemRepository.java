package com.example.bookstore.repositories;

import com.example.bookstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT count('customer_id') FROM cart_item where customer_id = :customer_id", nativeQuery = true)
    int getCartItemCountByUserId(@Param("customer_id") Long customer_id);

}

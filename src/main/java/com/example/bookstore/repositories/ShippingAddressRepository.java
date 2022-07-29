package com.example.bookstore.repositories;

import com.example.bookstore.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
    List<ShippingAddress> findAllByCustomerId(Long id);
}

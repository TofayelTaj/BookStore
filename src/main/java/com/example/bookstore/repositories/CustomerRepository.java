package com.example.bookstore.repositories;

import com.example.bookstore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Customer findCustomerByEmail(String email);
}

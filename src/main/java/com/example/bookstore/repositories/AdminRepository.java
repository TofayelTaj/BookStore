package com.example.bookstore.repositories;

import com.example.bookstore.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByEmail(String email);

}

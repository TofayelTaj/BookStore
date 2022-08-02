package com.example.bookstore.services;

import com.example.bookstore.entities.User;
import com.example.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }




}

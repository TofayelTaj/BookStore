package com.example.bookstore;

import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {
    @Autowired
    private CartItemService cartItemService;

    @Test
    void contextLoads() {

    }
}

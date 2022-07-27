package com.example.bookstore;

import com.example.bookstore.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookService bookService;
	@Test
	void contextLoads() {

	}




}

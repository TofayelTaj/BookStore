package com.example.bookstore.repositories;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query(value = "SELECT * FROM book where category_id = :categoryId", nativeQuery = true)
    List<Book> findBookByCategory(@Param("categoryId") Long categoryId);

}

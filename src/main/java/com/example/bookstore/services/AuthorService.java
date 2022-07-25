package com.example.bookstore.services;

import com.example.bookstore.entities.Author;
import com.example.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public List<Author>  getAllAuthors(){
        return authorRepository.findAll();
    }

}

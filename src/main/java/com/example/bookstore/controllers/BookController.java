package com.example.bookstore.controllers;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Category;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAddBookPage(Model model){
        List<Author> allAuthor = authorService.getAllAuthors();
        List<Category> categories = categoryService.getAllCategory();

        model.addAttribute("allAuthor", allAuthor);
        model.addAttribute("allCategory", categories);

        return "add-book";
    }

    @GetMapping("/all")
    public List<Book> allBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping()
    public String createNewBook( Book book){
        bookService.createBook(book);
        return "redirect:/book";
    }

}

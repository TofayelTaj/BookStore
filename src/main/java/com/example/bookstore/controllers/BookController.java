package com.example.bookstore.controllers;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.services.AdminService;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getAddBookPage(Model model){
        List<Author> allAuthor = authorService.getAllAuthors();
        model.addAttribute("allAuthor", allAuthor);

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

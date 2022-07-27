package com.example.bookstore.controllers;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Category;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CategoryService;
import com.example.bookstore.services.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
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
    @Autowired
    private HomePageService homePageService;

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


    @GetMapping("/category/{categoryId}")
    public ModelAndView getBooksByCategoryId(@PathVariable Long categoryId, Principal principal){
       return homePageService.getIndexPage(principal, bookService.findBookByCategoryId(categoryId), categoryService.getAllCategory());
    }

}

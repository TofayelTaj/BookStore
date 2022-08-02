package com.example.bookstore.services;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Category;
import com.example.bookstore.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Service
public class HomePageService {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService customerService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    public ModelAndView getIndexPage( Principal principal, List<Book> books, List<Category> categories){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        User user = null;
        if(principal != null) {
            user = customerService.findByEmail(principal.getName());
            modelAndView.addObject("cartItemCount", cartItemService.getCartItemCount(user.getId()));
            modelAndView.addObject("cartItems", cartItemService.getCartItemsByCustomerId(user.getId()));
            modelAndView.addObject("orders", orderService.orderListByUserId(user.getId()));
            modelAndView.addObject("addressList", shippingAddressService.getAllShippingAddressByCustomerId(user.getId()));        }
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}

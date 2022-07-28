package com.example.bookstore.controllers;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CartItem;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.entities.Order;
import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.services.CartItemService;
import com.example.bookstore.services.CustomerService;
import com.example.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order" )
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping()
    public String doOrder(HttpServletRequest request, Principal principal){

        Customer customer = customerService.findByEmail(principal.getName());
        List<CartItem> cartItems = cartItemService.getCartItemsByCustomerId(customer.getId());
        List<Book> books = new ArrayList<>();

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING.name());
        for(CartItem cartItem : cartItems){
            order.setBook(cartItem.getBook());
            orderService.saveOrder(order);
        }
        cartItemService.formatCart(customer.getId());
        return "redirect:" + request.getHeader("referer");
    }

}

package com.example.bookstore.controllers;

import com.example.bookstore.entities.CartItem;
import com.example.bookstore.entities.Order;
import com.example.bookstore.entities.ShippingAddress;
import com.example.bookstore.entities.User;
import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order" )
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping()
    public String doOrder(HttpServletRequest request, @RequestParam Long addressId, Principal principal){

        User user = userService.findByEmail(principal.getName());
        ShippingAddress address = shippingAddressService.findById(addressId);
        List<CartItem> cartItems = cartItemService.getCartItemsByCustomerId(user.getId());


        for(CartItem cartItem : cartItems){
            Order order = new Order();
            order.setUser(user);
            order.setShippingAddress(address);
            order.setStatus(OrderStatus.PENDING.name());
            order.setBook(cartItem.getBook());
            orderService.saveOrder(order);
        }
        cartItemService.formatCart(user.getId());
        return "redirect:" + request.getHeader("referer");
    }


    @GetMapping("/all")
    public ModelAndView getAllCartItemByCustomerId(Principal principal){
        User user = userService.findByEmail(principal.getName());
        ModelAndView modelAndView = homePageService.getIndexPage(principal, null, null);
        List<Order> orders = orderService.orderListByUserId(user.getId());
        modelAndView.setViewName("order-details");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView cartItemFilterByStatus(@RequestParam String status, Principal principal){

        if(status.equals("all")){
            return new ModelAndView("redirect:/order/all");
        }
        User user = userService.findByEmail(principal.getName());
        ModelAndView modelAndView = homePageService.getIndexPage(principal, null, null);
        List<Order> orders = orderService.findOrdersByUserIdAndStatus(user.getId(), status);
        modelAndView.setViewName("order-details");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

}

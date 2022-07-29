package com.example.bookstore.controllers;

import com.example.bookstore.entities.Customer;
import com.example.bookstore.entities.ShippingAddress;
import com.example.bookstore.repositories.ShippingAddressRepository;
import com.example.bookstore.services.CustomerService;
import com.example.bookstore.services.HomePageService;
import com.example.bookstore.services.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/address")
public class ShippingAddressController {

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping
    public ModelAndView getAddressPage(Principal principal) {
        ModelAndView modelAndView = homePageService.getIndexPage(principal, null, null);

        modelAndView.setViewName("shipping-address");
        return modelAndView;
    }

    @GetMapping("/add")
    public String addShippingAddress(@ModelAttribute ShippingAddress address, HttpServletRequest request, Principal principal) {
        Customer customer = customerService.findByEmail(principal.getName());
        address.setCustomer(customer);
        shippingAddressRepository.save(address);

        return "redirect:" + request.getHeader("referer");
    }


}

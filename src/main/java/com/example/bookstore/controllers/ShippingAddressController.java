package com.example.bookstore.controllers;

import com.example.bookstore.entities.ShippingAddress;
import com.example.bookstore.entities.User;
import com.example.bookstore.repositories.ShippingAddressRepository;
import com.example.bookstore.services.HomePageService;
import com.example.bookstore.services.ShippingAddressService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/address")
public class ShippingAddressController {

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private UserService userService;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping
    public ModelAndView getAddressPage(Principal principal,
                                       Model model,
                                       HttpServletRequest request) {
        ModelAndView modelAndView = homePageService.getIndexPage(principal, null, null);

        if(RequestContextUtils.getInputFlashMap(request) != null) {
            model.addAttribute("addressError", RequestContextUtils.getInputFlashMap(request).get("addressError"));
        }
        modelAndView.setViewName("shipping-address");
        return modelAndView;
    }

    @GetMapping("/add")
    public String addShippingAddress(@Valid @ModelAttribute ShippingAddress address,
                                     BindingResult result,
                                     HttpServletRequest request,
                                     Principal principal,
                                     RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("addressError",result.getFieldErrors("addressLine").get(0).getDefaultMessage());
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);

            return "redirect:" + request.getHeader("Referer");
        }


        User user = userService.findByEmail(principal.getName());
        address.setUser(user);
        shippingAddressRepository.save(address);

        return "redirect:" + request.getHeader("referer");
    }


}

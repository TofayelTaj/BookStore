package com.example.bookstore.security;

import com.example.bookstore.enums.UserType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
       String path = "";
       ApplicationUserDetails user = (ApplicationUserDetails) authentication.getPrincipal();
       List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) user.getAuthorities();
        if(authorities.get(0).toString().contains(UserType.CUSTOMER.toString())){
            path = "/";
        }else {
            path = "/admin/?status=all";
        }
        response.sendRedirect(path);
    }
}

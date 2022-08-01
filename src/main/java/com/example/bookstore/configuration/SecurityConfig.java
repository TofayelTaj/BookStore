package com.example.bookstore.configuration;

import com.example.bookstore.enums.UserType;
import com.example.bookstore.security.AdminDetailsServiceImpl;
import com.example.bookstore.security.CustomerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private PasswordConfig passwordConfig;

    @Bean
    protected UserDetailsService adminUserDetailsService() {
        return new AdminDetailsServiceImpl();
    }

    @Bean
    protected UserDetailsService customerUserDetailsService(){
        return new CustomerDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/customer/**",
                        "/resourceURL/**",
                        "/admin/register",
                        "/login",
                        "/logout",
                        "/book/category/*"
                )
                .permitAll()
                .antMatchers("/admin")
                .hasAuthority(UserType.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }


    @Bean
    protected DaoAuthenticationProvider adminDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.adminUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    protected DaoAuthenticationProvider customerDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.customerUserDetailsService());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.adminDaoAuthenticationProvider())
                .authenticationProvider(this.customerDaoAuthenticationProvider());
    }
}

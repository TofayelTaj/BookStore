package com.example.bookstore.configuration;

import com.example.bookstore.security.AdminDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordConfig passwordConfig;

    @Bean
    protected UserDetailsService adminUserDetailsService() {
        return new AdminDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/admin", "/admin/register", "/login", "/logout" )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }


    @Bean
    protected DaoAuthenticationProvider adminDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.adminUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.adminDaoAuthenticationProvider());
    }
}

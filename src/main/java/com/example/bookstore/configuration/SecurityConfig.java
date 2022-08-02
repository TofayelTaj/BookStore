package com.example.bookstore.configuration;

import com.example.bookstore.enums.UserType;
import com.example.bookstore.security.UserDetailsServiceImpl;
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
    protected UserDetailsService applicationUserDetailsService(){
        return new UserDetailsServiceImpl();
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
                        "/user/sign-up",
                        "/admin/register",
                        "/login",
                        "/logout",
                        "/book/category/*"
                )
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole(UserType.ADMIN.toString())
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
    protected DaoAuthenticationProvider applicationUserDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.applicationUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.applicationUserDaoAuthenticationProvider());

    }
}

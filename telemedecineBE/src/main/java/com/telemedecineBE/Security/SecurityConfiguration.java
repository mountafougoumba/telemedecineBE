package com.telemedecineBE.Security;





import com.telemedecineBE.dao.AdminRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.entities.Admin;
import com.telemedecineBE.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

// Basic Login neeed to connect to databse


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDao userDao;
    @Autowired
    AdminRepository adminRepository;
    @Override
    public void configure(HttpSecurity http) throws Exception{


        http

                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/register",
                        "/login", "/logout",
                        "/patient", "/patient/**", "/patients",
                        "/address", "/addresses", "/address/**",
                        "/insurance", "/insurances", "/insurance/**",
                        "/user", "/users", "/user/**",
                        "/doctors", "/doctor", "/doctor/**",
                        "/admin", "/admins", "/admin/**",
                        "/request", "/requests", "/request/**",
                        "/message", "/messages", "/message/**","/messages/**",
                        "/prescriptions", "/prescription", "/prescription/**",
                        "/appointment", "/appointments", "/appointment/**",
                        "/report", "/reports", "/report/**").permitAll()
                .anyRequest().authenticated()
               // .and()
                //.formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        List<Admin> admins = adminRepository.findAll();
        for (Admin user: admins) {
            auth.inMemoryAuthentication()
                    .withUser(user.getUserName())
                    .password(user.getUserpassword())
                    .roles("ADMIN");
        }
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("B")
                .password(passwordEncoder().encode("1234"))
                .roles("USER");
    }



    @Bean
    public PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
    }
}



package com.telemedecineBE.Security;





import com.telemedecineBE.dao.AdminRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// Basic Login neeed to connect to databse


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDao userDao;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsServiceImplenets userDetailsServiceImplenets;
    @Autowired
    JwtFilter jwtFilter;

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }

    public void SecurityConfig(UserRepository userRepository){
        this.userRepository = userRepository;

    }


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http = http.csrf().disable().cors().disable();


        http = http.sessionManagement()
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                               .and();

        http = http.exceptionHandling()
                        .authenticationEntryPoint((request, responce, ex) ->{
                                responce.sendError(HttpServletResponse.SC_UNAUTHORIZED , ex.getMessage());
                                }).and();



        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/validate","/authenticate").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/login", "/logout").permitAll()
                .antMatchers(HttpMethod.PUT, "/register").permitAll()
                .anyRequest().authenticated()
               // .and()
                //.formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .addLogoutHandler((request, response, authentication) -> {

                })
                .logoutSuccessUrl("/login")
                .permitAll();

        http.addFilterBefore(
                jwtFilter,UsernamePasswordAuthenticationFilter.class
        );
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userDetailsServiceImplenets).passwordEncoder(passwordEncoder());







        List<Admin> admins = adminRepository.findAll();
        for (Admin user: admins) {
            auth.inMemoryAuthentication()
                    .withUser(user.getUsername())
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



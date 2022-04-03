package com.telemedecineBE.web;

import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientRepository patientRepository;



    //login
    //@CrossOrigin(origins = "http://localhost:4200/login")
    @RequestMapping("/login")
    public String login(String userName, String userpassword){
        try {
            User user= userDao.findUserByUserNameAndUserpassword(userName,userpassword);
        }
        catch (Exception ex) {
            return "Error Invalid User Name or Password for: " + userName;
        }
        return "Login Page";
    }

    //logout
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }



    @PostMapping("/user")
    User newUser(@RequestBody User user){
        Boolean exists = userDao.existsByEmail(user.getEmail());
        //Boolean exists2 = patientRepository.existsByPhone(patient.getPhone());
        if(exists){
            throw new IllegalStateException("Patient with email " + user.getEmail() + " already exists.");
        } //else if(exists2){
        //throw new IllegalStateException("Patient with phone " + patient.getPhone() + " already exists.");
        //}
        String test;
        user.setUserType("PATIENT");

        userDao.save(user);
        System.out.println("newPatient");

        return user;
    }
    // Create User
    @GetMapping("/register")
    @ResponseBody
    public String create(String userName, String userpassword){
        User user = null;
        try {
            user = new User(userName, passwordEncoder.encode(userpassword));
            userDao.save(user);
        }
        catch (Exception ex){
            return "Error creating user:" + userName;
        }
        return "User registered succesfully! (username = " + user.getUserName() + ")";
    }

   /*@RequestMapping("/update")
    @ResponseBody
    public String updateUser(Integer id,String username, String name) {
        User user = (User) userDao.findByUserName(username);
        userDao.save(user);
        // user.setUserName(username);
        user.setFname(name);
        return "User succesfully updated: ";
    }

    */





}

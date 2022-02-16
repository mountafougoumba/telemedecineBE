package com.telemedecineBE.web;

import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.entities.User;
import com.telemedecineBE.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;



    //login
    @RequestMapping("/login")
    public String login(String username, String password){
        try {
            User user= userDao.findByUserName(username);
        }
        catch (Exception ex) {
            return "Error updating the user: " + username;
        }
        return "Login Page";
    }

    // Create User
    @GetMapping("/register")
    @ResponseBody
    public String create(String userName, String userpassword){
        User user = null;
        try {
            user = new User(userName, userpassword);
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

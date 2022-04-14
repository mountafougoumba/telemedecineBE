package com.telemedecineBE.web;

import  com.telemedecineBE.TelemedecineBeApplication;
import com.telemedecineBE.entities.User;
import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200/return")
public class UserController {

    @Autowired
    private UserDao userDao;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
     */
    @PostMapping("/login")
    User loginUser(@RequestBody Map<String, String> userMap){
        String email = userMap.get("email");
        String password = userMap.get("password");
        Boolean validUser = userDao.existsByEmail(email);

        if(!validUser) {
            throw new IllegalStateException("User with email " + email + " does not exist");
        }

        User user = userDao.findByEmail(email);

        if(!BCrypt.checkpw(password, user.getUserpassword())) {
            throw new IllegalStateException("Incorrect password");
        }

        /**
         * Setting the password to this string so it doesn't display the hash on the front end
         * Do not save this. Only used to hide the password on the frontend
         */
        user.setUserpassword("*****");

        return user;
    }
    
    @GetMapping("/users")
    List<User> getAllUsers(){
        System.out.println("getAllUsers");
        return userDao.findAll();
    }

    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientRepository patientRepository;

    @PutMapping("/user/id={id}")
    User updateUserById(@PathVariable(value = "id")Integer id,
                        @RequestBody User user){
        Boolean exists = userDao.existsById(id);
        if(!exists){
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        User currentUser = userDao.findById(id);

        if(user.getLname() != null && user.getLname().length() > 0 && user.getLname() != currentUser.getLname()){
            currentUser.setLname(user.getLname());
        }

        if(user.getFname() != null && user.getFname().length() > 0 && user.getFname() != currentUser.getFname()){
            currentUser.setFname(user.getFname());
        }

        if(user.getUserName() != null && user.getUserName() .length() > 0 && user.getUserName()  != currentUser.getUserName() && !userDao.existsByUserName(user.getUserName())){
            currentUser.setUserName(user.getUserName() );
        }

        if(user.getUserpassword() != null && user.getUserpassword().length() > 0 && user.getUserpassword() != currentUser.getUserpassword()){
            currentUser.setUserpassword(user.getUserpassword());
        }

        if(user.getUserType() != null && user.getUserType() != currentUser.getUserType()){
            currentUser.setUserType(user.getUserType());
        }

        if(user.getEmail() != null && user.getEmail().length() > 0 && user.getEmail() != currentUser.getEmail() && !userDao.existsByEmail(user.getEmail())){
            currentUser.setEmail(user.getEmail());
        }

        if(user.getCellphone() != null && user.getCellphone().length() > 0 && user.getCellphone() != currentUser.getCellphone() && !userDao.existsByCellphone(user.getCellphone())){
            currentUser.setCellphone(user.getCellphone());
        }

        if(user.getState() != null && user.getState() > 0 && user.getState() != currentUser.getState()){
            currentUser.setState(user.getState());
        }

        System.out.println("updateUserById");
        userDao.save(currentUser);
        return currentUser;
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


    @PutMapping("/user/email={email}")
    User updateUserByEmail(@PathVariable(value = "email")String currentEmail,
                        @RequestParam(required = false)String lName,
                        @RequestParam(required = false)String fName,
                        @RequestParam(required = false)String username,
                        @RequestParam(required = false)String password,
                        @RequestParam(required = false)UserType userType,
                        @RequestParam(required = false)String email,
                        @RequestParam(required = false)String phone,
                        @RequestParam(required = false)Integer state){
        Boolean exists = userDao.existsByEmail(currentEmail);
        if(!exists){
            throw new IllegalStateException("User with email " + currentEmail + " does not exist.");
        }
        User user = userDao.findByEmail(currentEmail);


        if(fName != null && fName.length() > 0 && fName != user.getFname()){
            user.setFname(fName);
        }

        if(username != null && username.length() > 0 && username != user.getUserName() && !userDao.existsByUserName(username)){
            user.setUserName(username);
        }

        if(password != null && password.length() > 0 && password != user.getUserpassword()){
            user.setUserpassword(password);
        }

        if(userType != null && userType != user.getUserType()){
            user.setUserType(userType);
        }

        if(email != null && email.length() > 0 && email != user.getEmail() && !userDao.existsByEmail(email)){
            user.setEmail(email);
        }

        if(phone != null && phone.length() > 0 && phone != user.getCellphone() && !userDao.existsByCellphone(phone)){
            user.setCellphone(phone);
        }

        if(state != null && state > 0 && state != user.getState()){
            user.setState(state);
        }
        userDao.save(user);
        return user;
    }

    @PutMapping("/user/phone={phone}")
    User updateUserByPhone(@PathVariable(value = "phone")String currentPhone,
                           @RequestParam(required = false)String lName,
                           @RequestParam(required = false)String fName,
                           @RequestParam(required = false)String username,
                           @RequestParam(required = false)String password,
                           @RequestParam(required = false)UserType userType,
                           @RequestParam(required = false)String email,
                           @RequestParam(required = false)String phone,
                           @RequestParam(required = false)Integer state){
        Boolean exists = userDao.existsByCellphone(currentPhone);
        if(!exists){
            throw new IllegalStateException("User with phone " + currentPhone + " does not exist.");
        }
        User user = userDao.findByCellphone(currentPhone);

        if(lName != null && lName.length() > 0 && lName != user.getLname()){
            user.setLname(lName);
        }

        if(fName != null && fName.length() > 0 && fName != user.getFname()){
            user.setFname(fName);
        }

        if(username != null && username.length() > 0 && username != user.getUserName() && !userDao.existsByUserName(username)){
            user.setUserName(username);
        }

        if(password != null && password.length() > 0 && password != user.getUserpassword()){
            user.setUserpassword(password);
        }

        if(userType != null && userType != user.getUserType()){
            user.setUserType(userType);
        }

        if(email != null && email.length() > 0 && email != user.getEmail() && !userDao.existsByEmail(email)){
            user.setEmail(email);
        }

        if(phone != null && phone.length() > 0 && phone != user.getCellphone() && !userDao.existsByCellphone(phone)){
            user.setCellphone(phone);
        }

        if(state != null && state > 0 && state != user.getState()){
            user.setState(state);
        }

        System.out.println("updateUserByPhone");
        userDao.save(user);
        return user;
    }


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
}

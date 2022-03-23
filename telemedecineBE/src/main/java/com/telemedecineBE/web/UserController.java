package com.telemedecineBE.web;

import com.telemedecineBE.entities.User;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
        if(!user.getUserpassword().equals(password)) {
            throw new IllegalStateException("Incorrect password");
        }

        return user;
    }
    
    @GetMapping("/users")
    List<User> getAllUsers(){
        System.out.println("getAllUsers");
        return userDao.findAll();
    }

    @GetMapping("/user/id={id}")
    User getUserById(@PathVariable(value="id")Integer id){
        Boolean exists = userDao.existsById(id);
        if(!exists){
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        System.out.println("getUserById");
        return userDao.findById(id);
    }

    @GetMapping("/user/email={email}")
    User getUserByEmail(@PathVariable(value="email")String email){
        Boolean exists = userDao.existsByEmail(email);
        if(!exists){
            throw new IllegalStateException("User with email " + email + " does not exist");
        }
        System.out.println("getUserByEmail");
        return userDao.findByEmail(email);
    }

    @GetMapping("/user/phone={phone}")
    User getUserByCellPhone(@PathVariable(value="phone")String phone){
        Boolean exists = userDao.existsByCellphone(phone);
        if(!exists){
            throw new IllegalStateException("User with phone " + phone + " does not exist");
        }
        System.out.println("getUserByCellphone");
        return userDao.findByCellphone(phone);
    }

    @PostMapping("/user")
    User newUser(@RequestBody User user){
        Boolean exists = userDao.existsByEmail(user.getEmail());
        Boolean exists2 = userDao.existsByCellphone(user.getCellphone());
        if(exists){
            throw new IllegalStateException("User with email " + user.getEmail() + " already exists");
        } else if(exists2){
            throw new IllegalStateException("User with phone " + user.getCellphone() + " already exists");
        }
        userDao.save(user);
        System.out.println("newUser");
        return user;
    }

    @DeleteMapping("/user/id={id}")
    void deleteUserById(@PathVariable(value = "id")Integer id){
        Boolean exists = userDao.existsById(id);
        if(!exists){
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        System.out.println("deleteUserById");
        userDao.deleteById(id);
    }

    @DeleteMapping("/user/email={email}")
    void deleteUserByEmail(@PathVariable(value = "email")String email){
        Boolean exists = userDao.existsByEmail(email);
        if(!exists){
            throw new IllegalStateException("User with email " + email + " does not exist");
        }
        System.out.println("deleteUserByEmail");
        userDao.deleteByEmail(email);
    }

    @DeleteMapping("/user/phone={phone}")
    void deleteUserByCellphone(@PathVariable(value = "phone")String phone){
        Boolean exists = userDao.existsByCellphone(phone);
        if(!exists){
            throw new IllegalStateException("User with phone " + phone + " does not exist");
        }
        System.out.println("deleteUserByCellphone");
        userDao.deleteByCellphone(phone);
    }

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

        System.out.println("updateUserByEmail");
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

     /*Create User. Edit to add encoded passwords to database
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
    } */

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

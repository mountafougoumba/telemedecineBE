package com.telemedecineBE.web;


import com.telemedecineBE.Security.JwtUtil;
import com.telemedecineBE.dao.RoleRepository;
import com.telemedecineBE.entities.User;

import com.telemedecineBE.dao.PatientRepository;

import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.enumeration.UserType;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin(origins = "http://localhost:4200/return")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
     */

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    User loginUser(@RequestBody Map<String, String> userMap){
        String email = userMap.get("userName");
        String password = userMap.get("userpassword");
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



    @PostMapping("/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody User user)throws Exception {
        System.out.println("Authenticating");
        System.out.println(user.getUsername());
        System.out.println(user.getUserpassword());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword()
                ));


        user = (User) authenticate.getPrincipal();
        user.setUserpassword(null);
        System.out.println(user.getPassword());
        System.out.println();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("JWT-TOKEN", jwtUtil.generateToken(user));
        responseHeaders.add("Access-Control-Exposed", "Authorization");


           /* ResponseEntity.ok().header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(user)//, jwtUtil.generateToken(user)
                    )
                    .body(user);

            */
        return ResponseEntity.ok(jwtUtil.generateToken(user));
    }






    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user){
        try {
            Boolean isTokenValid = jwtUtil.validateToken(token, user);
            return ResponseEntity.ok(isTokenValid);
            }
        catch (ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }


    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        System.out.println("getAllUsers");
        return userDao.findAll();
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
        user.setUserType(UserType.PATIENT);


        userDao.save(user);
        System.out.println("newPatient");

        return user;
    }
    // Create User
    @GetMapping("/register")
    @ResponseBody
    public String create(String userName, String userpassword) {
        User user = null;
        try {
            user = new User(userName, passwordEncoder.encode(userpassword));
            userDao.save(user);
        }
        catch (Exception ex){
            return "Error creating user:" + userName;
        }
        return "User registered succesfully! (username = " + user.getUsername() + ")";
    }


    


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

        if(user.getUsername() != null && user.getUsername() .length() > 0 && user.getUsername()  != currentUser.getUsername() && !userDao.existsByUserName(user.getUsername())){
            currentUser.setUserName(user.getUsername() );
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
                        @RequestParam(required = false) UserType userType,
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

        if(username != null && username.length() > 0 && username != user.getUsername() && !userDao.existsByUserName(username)){
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

    /*
    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        Boolean exists = userDao.existsByEmail(user.getEmail());
        //Boolean exists2 = patientRepository.existsByPhone(patient.getPhone());
        if (exists) {
            throw new IllegalStateException("Patient with email " + user.getEmail() + " already exists.");
        } //else if(exists2){
        //throw new IllegalStateException("Patient with phone " + patient.getPhone() + " already exists.");
        //}
        String test;
        user.setUserType("PATIENT");
    }
*/

        @PutMapping("/user/phone={phone}")
    User updateUserByPhone(@PathVariable(value = "phone")String currentPhone,
                           @RequestParam(required = false)String lName,
                           @RequestParam(required = false)String fName,
                           @RequestParam(required = false)String username,
                           @RequestParam(required = false)String password,
                           @RequestParam(required = false) UserType userType,
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

        if(username != null && username.length() > 0 && username != user.getUsername() && !userDao.existsByUserName(username)){
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

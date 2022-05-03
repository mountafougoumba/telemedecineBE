package com.telemedecineBE.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telemedecineBE.dao.DoctorRepository;
import com.telemedecineBE.dao.UserRepository;
import com.telemedecineBE.entities.User;
import org.springframework.web.bind.annotation.*;

import com.telemedecineBE.Security.AES;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    private UserRepository userRepository;

    SearchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/search/{term}")
    Map<String, String> searchByTerm(@PathVariable(value="term")String term) {

       try {
           List<User> users = this.userRepository.searchUsers(term);
           System.out.println(users);

           ObjectMapper mapper = new ObjectMapper();

           HashMap<String, String> map = new HashMap<>();
           map.put("users", mapper.writeValueAsString(users));
           map.put("foo", "bar");
           map.put("aa", "bb");

           return map;
       } catch (Exception e) {
            return new HashMap<>();
       }
    }
}

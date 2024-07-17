package com.colibris.Colibris.controller;

import com.colibris.Colibris.model.User;
import com.colibris.Colibris.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/users")
    public User addUser(@RequestBody Map<String, Object> requestBody) {
        String username = requestBody.get("username").toString();
        String email = requestBody.get("email").toString();
        String password = requestBody.get("password").toString();
        User user = new User(username, email, password);
        return userRepository.save(user);
    }
}

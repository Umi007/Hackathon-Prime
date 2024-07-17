package com.colibris.Colibris.controller;

import com.colibris.Colibris.model.User;
import com.colibris.Colibris.repository.BookRepository;
import com.colibris.Colibris.repository.LoanRepository;
import com.colibris.Colibris.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/users")
    public User addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }
}

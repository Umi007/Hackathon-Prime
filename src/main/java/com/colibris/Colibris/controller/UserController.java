package com.colibris.Colibris.controller;

import com.colibris.Colibris.repository.BookRepository;
import com.colibris.Colibris.repository.LoanRepository;
import com.colibris.Colibris.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;
}

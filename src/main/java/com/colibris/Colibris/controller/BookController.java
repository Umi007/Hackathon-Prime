package com.colibris.Colibris.controller;

import com.colibris.Colibris.model.Book;
import com.colibris.Colibris.model.Loan;
import com.colibris.Colibris.model.User;
import com.colibris.Colibris.repository.BookRepository;
import com.colibris.Colibris.repository.LoanRepository;
import com.colibris.Colibris.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;


    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/books/{book_id}")
    public Book getBook(@PathVariable Integer book_id) {
        Optional<Book> optionalBook = bookRepository.findById(book_id);
        if (optionalBook.isEmpty()) return new Book();
        Book book = optionalBook.get();
        book.setLent(isBookLent(book));
        return book;
    }

    @GetMapping("/api/users/{user_id}/books")
    public List<Book> getAllUserBooks(@PathVariable Integer user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isEmpty()) return new ArrayList<Book>();
        List<Book> books = bookRepository.findByOwner(optionalUser.get());
        for (Book book : books) book.setLent(isBookLent(book));
        return bookRepository.findByOwner(optionalUser.get());
    }

    @PostMapping("/api/users/{user_id}/books/new")
    public List<Book> createUserBook(@PathVariable Integer user_id, @RequestBody Book book) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isEmpty()) return new ArrayList<Book>();
        //TODO Authentication Check "You can only add books to your own account"
        book.setOwner(optionalUser.get());
        bookRepository.save(book);
        return bookRepository.findByOwner(optionalUser.get());
    }

    @PostMapping("/api/users/{user_id}/books/delete")
    public List<Book> deleteBook(@PathVariable Integer user_id, @RequestBody Book book) {
        if (isBookLent(book)){
            //TODO error message "Cannot delete currently loaned book"
        }
        else bookRepository.delete(book);
        return getAllUserBooks(user_id);

    }

    private Boolean isBookLent(Book book){
        Optional<Loan> optionalLoan = loanRepository.findByBookAndIsActive(book, true);
        return optionalLoan.isPresent();
    }
}

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
public class LoanController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;


    @GetMapping("/api/users/{user_id}/loans")
    public List<Loan> getAllUserLoans(@PathVariable Integer user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isEmpty()) return new ArrayList<Loan>();
        return loanRepository.findByBorrowerAndIsActive(optionalUser.get(), true);
    }

//    rework mapping of resource
//    @PostMapping("/api/users/{user_id}/books")
//    public List<Book> deactivateLoan(@PathVariable Integer user_id, @RequestBody Book loanedBook) {
//        Optional<User> optionalUser = userRepository.findById(user_id);
//        if (optionalUser.isEmpty()) return new ArrayList<Book>();
//        //TODO Authenticate user owns book
//        Optional<Loan> optionalLoan = loanRepository.findByBookAndIsActive(loanedBook, true);
//        if (optionalLoan.isEmpty()) {
//            //TODO error message "Can't return book that isn't lent"
//        }
//        else{
//            Loan loan = optionalLoan.get();
//            loan.setActive(false);
//            loanRepository.save(loan);
//        }
//        List<Book> books = bookRepository.findByOwner(optionalUser.get());
//        for (Book book : books) book.setLent(isBookLent(book));
//        return bookRepository.findByOwner(optionalUser.get());
//    }

    @PostMapping("api/books/{book_id}/loans")
    public List<Loan> borrowBook(@PathVariable Integer book_id, @RequestParam Integer user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isEmpty()) return new ArrayList<>();
        User user = optionalUser.get();

        Optional<Book> optionalBook = bookRepository.findById(book_id);
        if (optionalBook.isEmpty()) {
            // TODO error message "Add error handling"
            return new ArrayList<>();
        }
        Book book = optionalBook.get();
        if (isBookLent(book)) {
            // TODO error message "cannot borrow a book that is already lent"
            return new ArrayList<>();
        }

        Loan loan = new Loan(user, book);
        loanRepository.save(loan);

        return getAllUserLoans(user_id);
    }

    // TODO: move this to BookController
    private List<Book> getBooksFromLoans(List<Loan> loans){
        return loans.stream().map(Loan::getBook).toList();
    }

    private Boolean isBookLent(Book book){
        Optional<Loan> optionalLoan = loanRepository.findByBookAndIsActive(book, true);
        return optionalLoan.isPresent();
    }
}

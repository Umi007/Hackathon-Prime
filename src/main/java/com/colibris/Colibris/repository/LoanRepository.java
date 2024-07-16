package com.colibris.Colibris.repository;
import com.colibris.Colibris.model.Loan;
import com.colibris.Colibris.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Integer> {
    public Optional<Loan> findById(Integer id);
    public List<Loan> findByBorrower(User borrower);
    public List<Loan> findByBorrowerAndIsActive(User borrower, boolean isActive);
    public List<Loan> findAll();
}
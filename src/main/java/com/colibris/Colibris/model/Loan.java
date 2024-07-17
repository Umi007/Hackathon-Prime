package com.colibris.Colibris.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Specifies that this class is an entity and will be mapped to a database table
@Table(name = "LOANS")
public class Loan {
    // Specifies the primary key of the entity
    @Id
    // Specifies how the primary key should be generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;
    @Column
    private Timestamp created_at;
    @Column
    private Timestamp expires_at;
    @Column
    private boolean isActive;

    // Default constructors for the Condition class.
    public Loan(User borrower, Book book)
    {
        this.book = book;
        this.borrower = borrower;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.expires_at = new Timestamp(System.currentTimeMillis() + 604800000);
        this.isActive = true;
    }
}
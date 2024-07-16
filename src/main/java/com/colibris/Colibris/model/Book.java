package com.colibris.Colibris.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Specifies that this class is an entity and will be mapped to a database table
@Table(name = "BOOKS")
public class Book {
    // Specifies the primary key of the entity
    @Id
    // Specifies how the primary key should be generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    private String genre;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Transient
    private boolean isLent;

    // Default constructor for the Condition class.
    public Book(int id, String title, String author, User owner)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.owner = owner;
    }
}
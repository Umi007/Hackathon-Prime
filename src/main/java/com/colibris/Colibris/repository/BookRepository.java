package com.colibris.Colibris.repository;
import com.colibris.Colibris.model.Book;
import com.colibris.Colibris.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Optional<Book> findById(Integer id);
    public List<Book> findByTitle(String title);
    public List<Book> findByGenre(String genre);

}
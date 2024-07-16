package com.colibris.Colibris.repository;
import com.colibris.Colibris.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findById(Integer id);
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
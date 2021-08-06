package com.example.mongodb.user.domain;


import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(String userId);

    User save(User user);
}

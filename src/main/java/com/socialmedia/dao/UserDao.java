package com.socialmedia.dao;

import com.socialmedia.model.User;
import java.util.Optional;

public interface UserDao {
    boolean createUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}


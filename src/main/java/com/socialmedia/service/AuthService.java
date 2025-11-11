package com.socialmedia.service;

import com.socialmedia.dao.UserDao;
import com.socialmedia.dao.UserDaoImpl;
import com.socialmedia.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Optional;

public class AuthService {
    private final UserDao userDao = new UserDaoImpl();
    public boolean register(String username, String email, String password){
        if (userDao.findByUsername(username).isPresent() || userDao.findByEmail(email).isPresent()){
            System.out.println("User already Exists");
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        return userDao.createUser(user);
    }
    public Optional<User> login(String username, String password){
        Optional<User> userOpt= userDao.findByUsername(username);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPasswordHash())){
                System.out.println("Logged in successfully......!"+user.getUsername());
                return Optional.of(user);
            }else{
                System.out.println("Invalid username or password!");
            }
        }else{
            System.out.println("No such user found!");
        }
        return Optional.empty();
    }
}

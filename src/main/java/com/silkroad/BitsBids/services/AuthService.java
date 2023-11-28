package com.silkroad.BitsBids.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.repositories.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(String name, String email, String password, String phoneNumber) {
        boolean userExists = userRepository.findUserByEmail(email).isPresent();
        if (userExists) {
            throw new IllegalStateException("User Already Exists");
        } else {
            return userRepository.save(new User(name, email,password, phoneNumber));
        }
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            return null;
        } else if(password.matches(user.get().getPassword())) {
            return userRepository.findUserByEmail(email).get();
        } else {
            return null;
        }

    }

}

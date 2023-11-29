package com.silkroad.BitsBids.services;

import com.silkroad.BitsBids.Hasher;
import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(String name, String email, String password, String phoneNumber, String hostelName) {
        boolean userExists = userRepository.findUserByEmail(email).isPresent();
        if (userExists) {
            throw new IllegalStateException("User Already Exists");
        } else {
            String hashedPassword = Hasher.get256Hash(password);
            return userRepository.save(new User(name, email,hashedPassword, phoneNumber,hostelName));
        }
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            return null;
        } else {
            String savedPassword = user.get().getPassword();
            String hashedPassword = Hasher.get256Hash(password);
            if (savedPassword.matches(hashedPassword)) {
                return userRepository.findUserByEmail(email).get();
            }
            return null;
        }

    }

    public Optional<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

}

package com.silkroad.BitsBids.controllers;

import com.silkroad.BitsBids.ResponseHandler;
import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = authService.register(registerRequest.name(), registerRequest.email(),
                    registerRequest.password(),
                    registerRequest.phoneNumber(),
                    registerRequest.hostelName()
                    );
            // Set null for security reasons
            user.setPassword(null);
            return ResponseHandler.generateResponse("User Successfully Registered", HttpStatus.CREATED,
                    user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("User already Exists", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest.email(), loginRequest.password());
        if (user == null) {
            return ResponseHandler.generateResponse("Error Occurred", HttpStatus.BAD_REQUEST, null);
        } else {
            // Set null for security reasons
            user.setPassword(null);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, user);
        }
    }

    public ResponseEntity<?> getUser(@RequestParam Long userId){
        Optional<User> user = authService.getUser(userId);
        if(user.isPresent()){
            return ResponseHandler.generateResponse("",HttpStatus.OK,user);
        } else {
            return ResponseHandler.generateResponse("User doesn't exist",HttpStatus.NOT_FOUND,null);
        }
    }
}

record RegisterRequest(String name, String email, String password, String phoneNumber, String hostelName) {
}

record LoginRequest(String email, String password) {
}
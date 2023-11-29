package com.silkroad.BitsBids.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.ResponseHandler;
import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.services.AuthService;

@CrossOrigin
@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = authService.register(registerRequest.name(), registerRequest.email(),
                    registerRequest.password(),
                    registerRequest.phoneNumber());
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
            return ResponseHandler.generateResponse("Error Occured", HttpStatus.BAD_REQUEST, user);
        } else {
            user.setPassword(null);
            return ResponseHandler.generateResponse("Sucess", HttpStatus.OK, user);
        }
    }
}

record RegisterRequest(String name, String email, String password, String phoneNumber) {
}

record LoginRequest(String email, String password) {
}
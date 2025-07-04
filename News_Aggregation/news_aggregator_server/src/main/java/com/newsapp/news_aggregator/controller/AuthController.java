package com.newsapp.news_aggregator.controller;

import com.newsapp.news_aggregator.model.User;
import com.newsapp.news_aggregator.service.AuthService;
import com.newsapp.news_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        boolean success = authService.signUp(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        if (success) {
            return ResponseEntity.ok("Sign-up successful.");
        } else {
            return ResponseEntity.badRequest().body("Sign-up failed. Please check input.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean success = authService.login(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Login failed. Invalid username or password.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok("Logged out.");
    }
}

package com.newsapp.news_aggregator.service;

import com.newsapp.news_aggregator.model.User;
import com.newsapp.news_aggregator.repository.UserRepository;
import com.newsapp.news_aggregator.util.UserLoginTracker;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepo;
    private User currentUser;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean signUp(String username, String email, String password, String role) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!email.endsWith("@gmail.com")) {
            return false;
        }
        if (email.startsWith("@gmail.com")) {
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        if (role == null || (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("USER"))) {
            return false;
        }
        if (userRepo.findByUsername(username) != null) {
            return false;
        }
        if (userRepo.findByEmail(email) != null) {
            return false;
        }

        User user = new User(username, email, password, role.toUpperCase());
        userRepo.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        currentUser = user;
        UserLoginTracker.setLoggedIn(true);
        return true;
    }

    public void logout() {
        currentUser = null;
        UserLoginTracker.setLoggedIn(false);
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
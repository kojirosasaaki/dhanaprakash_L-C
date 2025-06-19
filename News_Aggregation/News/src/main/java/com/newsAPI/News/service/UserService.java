package com.newsAPI.News.service;

import com.newsAPI.News.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}

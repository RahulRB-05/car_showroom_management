package com.example.AutoHub.user.service;

import com.example.AutoHub.user.entity.User;

public interface UserService {
    User createUser(User user);
    User getUserByUsername(String userName);
}

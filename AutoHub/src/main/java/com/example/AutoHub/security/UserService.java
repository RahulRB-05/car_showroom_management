package com.example.AutoHub.security;

public interface UserService {
    User createUser(User user);
    User getUserByUsername(String userName);
}

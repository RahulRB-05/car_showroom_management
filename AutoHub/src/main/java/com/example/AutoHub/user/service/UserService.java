package com.example.AutoHub.user.service;

import com.example.AutoHub.user.dto.UserRequestDTO;
import com.example.AutoHub.user.dto.UserResponseDto;
import com.example.AutoHub.user.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDTO userRequestDTO);

    User getUserById(Long userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    UserResponseDto updateUser(Long userId, UserRequestDTO userRequestDTO);

    String deleteUser(Long userId);
}

package com.example.AutoHub.user.service;

import com.example.AutoHub.exception.DuplicateEntryException;
import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.user.dto.UserRequestDTO;
import com.example.AutoHub.user.dto.UserResponseDto;
import com.example.AutoHub.user.entity.User;
import com.example.AutoHub.user.enums.Status;
import com.example.AutoHub.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new DuplicateEntryException("Email already exists");
        }

        User newUser=new User();
        newUser.setName(userRequestDTO.getName());
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setJoinDate(LocalDate.now());
        newUser.setPhoneNumber(userRequestDTO.getPhoneNumber());
        newUser.setStatus(Status.ACTIVE);
        newUser.setRole(userRequestDTO.getRole());

        userRepository.save(newUser);

       return mapToResponse(newUser);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->new NotFoundException("User not found!!!"));
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("User not found!!!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDTO userRequestDTO) {

        User existingUser = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!!!"));

        existingUser.setName(userRequestDTO.getName());
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setPhoneNumber(userRequestDTO.getPhoneNumber());
        existingUser.setRole(userRequestDTO.getRole());

         userRepository.save(existingUser);

        return mapToResponse(existingUser);
    }

    @Override
    public String deleteUser(Long userId) {
       User existingUser=userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!!!"));
       existingUser.setStatus(Status.INACTIVE);
       existingUser.setRelieveDate(LocalDate.now());
            userRepository.save(existingUser);

       return "User removed ";
    }

    public UserResponseDto mapToResponse(User user){
        UserResponseDto response=new UserResponseDto();
        response.setRole(user.getRole());
        response.setUserName(user.getName());

        return response;
    }
}

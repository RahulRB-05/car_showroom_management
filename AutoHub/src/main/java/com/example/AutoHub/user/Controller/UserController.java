package com.example.AutoHub.user.Controller;

import com.example.AutoHub.user.dto.UserRequestDTO;
import com.example.AutoHub.user.dto.UserResponseDto;
import com.example.AutoHub.user.entity.User;
import com.example.AutoHub.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

       private final UserService userService;

       // Constructor injection
       public UserController(UserService userService) {
              this.userService = userService;
       }

       // 1️⃣ Create user
       @PostMapping("/create")
       public UserResponseDto createUser(@RequestBody UserRequestDTO userRequestDTO) {
              return userService.createUser(userRequestDTO);
       }

       // 2️⃣ Get user by id
       @GetMapping("/user_by_id/{id}")
       public User getUserById(@PathVariable Long id) {
              return userService.getUserById(id);
       }

       // 3️⃣ Get all users
       @GetMapping("/all_users")
       public List<User> getAllUsers() {
              return userService.getAllUsers();
       }

       // 4️⃣ Update user
       @PutMapping("/update/{id}")
       public UserResponseDto updateUser(
               @PathVariable Long id,
               @RequestBody UserRequestDTO userRequestDTO) {
              return userService.updateUser(id,userRequestDTO);
       }

       // 5️⃣ Delete user
       @PutMapping("/delete/{id}")
       public String deleteUser(@PathVariable Long id) {
              return userService.deleteUser(id);
       }
}

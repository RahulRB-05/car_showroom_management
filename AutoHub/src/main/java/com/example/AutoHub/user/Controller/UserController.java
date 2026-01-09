package com.example.AutoHub.user.Controller;

import com.example.AutoHub.user.entity.User;
import com.example.AutoHub.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

       private final UserService userService;

       // Constructor injection
       public UserController(UserService userService) {
              this.userService = userService;
       }

       // 1️⃣ Create user
       @PostMapping
       public ResponseEntity<User> createUser(@RequestBody User user) {
              User createdUser = userService.createUser(user);
              return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
       }

       // 2️⃣ Get user by id
       @GetMapping("/{id}")
       public ResponseEntity<User> getUserById(@PathVariable Long id) {
              User user = userService.getUserById(id);
              return ResponseEntity.ok(user);
       }

       // 3️⃣ Get all users
       @GetMapping
       public ResponseEntity<List<User>> getAllUsers() {
              List<User> users = userService.getAllUsers();
              return ResponseEntity.ok(users);
       }

       // 4️⃣ Update user
       @PutMapping("/{id}")
       public ResponseEntity<User> updateUser(
               @PathVariable Long id,
               @RequestBody User user) {

              User updatedUser = userService.updateUser(id, user);
              return ResponseEntity.ok(updatedUser);
       }

       // 5️⃣ Delete user
       @DeleteMapping("/{id}")
       public ResponseEntity<String> deleteUser(@PathVariable Long id) {
              userService.deleteUser(id);
              return ResponseEntity.ok("User deleted successfully");
       }
}



package com.example.AutoHub.user.dto;

import com.example.AutoHub.user.enums.Role;
import com.example.AutoHub.user.enums.Status;

public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private Role role;
    private Status status;

    // No-arg constructor
    public UserDTO() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


package com.example.AutoHub.user.dto;

import com.example.AutoHub.user.enums.Role;
import com.example.AutoHub.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userName;
    private Role role;
    private Status status;
}

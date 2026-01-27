

package com.example.AutoHub.user.dto;

import com.example.AutoHub.user.enums.Role;
import com.example.AutoHub.user.enums.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[2-9][0-9]{9}$",
            message ="phone number must be 10 digits and Start with 6-9")
    private String email;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    private LocalDate joinDate;

    @Column(nullable = false)
    private Role role;
}


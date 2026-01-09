package com.example.AutoHub.customer.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotBlank(message = "Customer name is required")
    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(min = 10,max = 250,message = "Address must between 10 and 250 characters" )
    private String Address;

    @Pattern(regexp = "^[2-9][0-9]{9}$",
            message ="phone number must be 10 digits and Start with 6-9")
    private String phone;
}




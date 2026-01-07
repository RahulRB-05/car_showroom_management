package com.example.AutoHub.inventory.dto;

import com.example.AutoHub.inventory.enumclass.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {

    @NotBlank(message = "Brand name is required")
    private String brand;
    @NotBlank(message = "Model name is required")
    private String model;
    @NotNull(message = "ManufacturingYear can't be Empty")
    private Integer manufacturingYear;
    @NotNull(message = "Color is required")
    private String color;
    @NotBlank(message = "The VIN number must be entered")
    @Size(min = 11, max = 17)
    private String vin;
    @NotBlank(message = "RegistrationNumber Can't be Empty")
    private String registrationNumber;
    @NotNull(message = "vehicleType is required")
    private VehicleType vehicleType;
    @NotNull(message = "vehicleStatus is required")
    private VehicleStatus vehicleStatus;
    @NotNull(message = "vehicleCategory is required")
    private VehicleCategory vehicleCategory;
    @NotNull(message = "fuelType is required")
    private FuelType fuelType;
    @NotNull(message = "transmission is required")
    private Transmission transmission;
    @NotNull
    private Integer mileage;
    @NotNull(message = "price is required")
    private Double price;
    @NotNull(message = "purchaseDate is required")
    private LocalDate purchaseDate;
}

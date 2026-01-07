package com.example.AutoHub.inventory.entity;

import com.example.AutoHub.inventory.enumclass.*;
import org.hibernate.annotations.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "vehicles")
//@Where(clause = "vehicle_status <> 'DELETED'")
public class Vehicle {

    //    PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vechileId;

    //    BASIC DETAILS
    private String brand;
    private String model;   //like model name e.g( virtus,xuv)
    private Integer manufacturingYear;
    private String color;

    //    IDENTIFICATION
    @Column(unique = true, nullable = false)
    private String vin;   //Vehicle Identification Number this number will be unique for every vehicle
    @Column(unique = true, nullable = false)
    private String registrationNumber;

    //    VEHICLE CLASSIFICATION
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;      //NEW/USED
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;      //AVAILABLE/SOLD ETC..
    @Enumerated(EnumType.STRING)
    private VehicleCategory vehicleCategory;      //SUV,MUV ETC..

    //    TECHNICAL DETAILS
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;       //PETROL/EV
    @Enumerated(EnumType.STRING)
    private Transmission transmission;     //MANUAL/AUTOMATIC
    private Integer mileage;

    //    PRICING
    private Double price;
//    private Boolean isOnSale;

    //    DATES
    private LocalDate purchaseDate;
    private LocalDate createdDate;

}
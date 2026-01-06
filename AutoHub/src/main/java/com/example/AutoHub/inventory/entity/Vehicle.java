package com.example.AutoHub.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    private Long vehicleId;
}

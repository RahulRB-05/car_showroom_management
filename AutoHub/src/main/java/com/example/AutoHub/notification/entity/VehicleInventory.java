package com.example.AutoHub.notification.entity;

import com.example.AutoHub.inventory.enumclass.FuelType;
import com.example.AutoHub.inventory.enumclass.VehicleCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private String brand;
    private String model;

    private int stockCount;
    private int stockCountThreshold=3;
    private boolean lowStockAlertSent = false;

}

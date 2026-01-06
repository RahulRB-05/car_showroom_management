package com.example.AutoHub.vehicleservice.entity;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.vehicleservice.enumclass.ServiceStatus;
import com.example.AutoHub.vehicleservice.enumclass.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceRecordId;

    private LocalDate serviceDate;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;

    private double serviceCost;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

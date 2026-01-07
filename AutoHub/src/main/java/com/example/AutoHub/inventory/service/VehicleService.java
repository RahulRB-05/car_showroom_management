package com.example.AutoHub.inventory.service;

import com.example.AutoHub.inventory.dto.VehicleRequestDto;
import com.example.AutoHub.inventory.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    String addvehicle(VehicleRequestDto vehicleRequestDto);
    List<Vehicle> getallvehicle();
    List<Vehicle> getallvehiclebybrand(String brand);
    List<Vehicle> getallvehiclebymanufacturingYear(Integer year);
    List<Vehicle> getallvehiclebytype(String vehicleType);
    List<Vehicle> getallvehiclebyfueltype(String fuelType);
    List<Vehicle> getallvehiclebytransmission(String transmission);
    String updatevehicle(String vin,VehicleRequestDto vehicleRequestDto);
    String deletevehicle(String vin);




}

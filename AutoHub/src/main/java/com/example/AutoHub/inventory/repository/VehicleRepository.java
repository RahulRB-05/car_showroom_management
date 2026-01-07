package com.example.AutoHub.inventory.repository;

import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.enumclass.FuelType;
import com.example.AutoHub.inventory.enumclass.Transmission;
import com.example.AutoHub.inventory.enumclass.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    List<Vehicle> findAllBybrand(String brand);
    List<Vehicle> findAllBymanufacturingYear(Integer year);
    List<Vehicle> findAllByvehicleType(VehicleType vehicleType);
    List<Vehicle> findAllByfuelType(FuelType fueltype);
    List<Vehicle> findAllBytransmission(Transmission transmission);
    Void deleteByvin(String vin);
    Optional<Vehicle> findByvin(String vin);

}

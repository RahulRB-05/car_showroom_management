package com.example.AutoHub.notification.repository;

import com.example.AutoHub.notification.entity.VehicleInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<VehicleInventory,Long> {

    @Query("select v from VehicleInventory v where v.brand = :brand and v.model = :model")
    Optional<VehicleInventory> findInventoryByBrandAndModel(String brand,String model);
}

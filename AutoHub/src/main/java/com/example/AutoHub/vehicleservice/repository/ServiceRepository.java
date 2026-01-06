package com.example.AutoHub.vehicleservice.repository;

import com.example.AutoHub.vehicleservice.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceRecord,Long> {
    List<ServiceRecord> findByVehicleVehicleId(Long vehicleId);
    List<ServiceRecord> findByCustomerCustomerId(Long customerId);
}

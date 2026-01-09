package com.example.AutoHub.inventory.service;

import com.example.AutoHub.inventory.dto.VehicleRequestDto;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.enumclass.FuelType;
import com.example.AutoHub.inventory.enumclass.Transmission;
import com.example.AutoHub.inventory.enumclass.VehicleStatus;
import com.example.AutoHub.inventory.enumclass.VehicleType;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.sales.entity.SalesOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    @Override
    public String addvehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle=new Vehicle();
        vehicle.setBrand(vehicleRequestDto.getBrand());
        vehicle.setModel(vehicleRequestDto.getModel());
        vehicle.setManufacturingYear(vehicleRequestDto.getManufacturingYear());
        vehicle.setColor(vehicleRequestDto.getColor());
        vehicle.setVin(vehicleRequestDto.getVin());
        vehicle.setRegistrationNumber(vehicleRequestDto.getRegistrationNumber());
        vehicle.setVehicleType(vehicleRequestDto.getVehicleType());
        vehicle.setVehicleStatus(vehicleRequestDto.getVehicleStatus());
        vehicle.setVehicleCategory(vehicleRequestDto.getVehicleCategory());
        vehicle.setFuelType(vehicleRequestDto.getFuelType());
        vehicle.setTransmission(vehicleRequestDto.getTransmission());
        vehicle.setMileage(vehicleRequestDto.getMileage());
        vehicle.setPrice(vehicleRequestDto.getPrice());
        vehicle.setPurchaseDate(vehicleRequestDto.getPurchaseDate());
        vehicle.setCreatedDate(LocalDate.now());
        vehicle.setSales(new ArrayList<>());

        vehicleRepository.save(vehicle);
        return "Vehicle Added Successfully...";
    }

    @Override
    public List<Vehicle> getallvehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getallvehiclebybrand(String brand) {
        return vehicleRepository.findAllBybrand(brand);
    }

    @Override
    public List<Vehicle> getallvehiclebymanufacturingYear(Integer year) {
        return vehicleRepository.findAllBymanufacturingYear(year);
    }

    @Override
    public List<Vehicle> getallvehiclebytype(String vehicleType) {
        VehicleType typeenum=VehicleType.valueOf(vehicleType.toUpperCase());
        return vehicleRepository.findAllByvehicleType(typeenum);
    }

    @Override
    public List<Vehicle> getallvehiclebyfueltype(String fuelType) {
        FuelType enumfuel=FuelType.valueOf(fuelType.toUpperCase());
        return vehicleRepository.findAllByfuelType(enumfuel);
    }

    @Override
    public List<Vehicle> getallvehiclebytransmission(String transmission) {
        Transmission enumtransmission=Transmission.valueOf(transmission.toUpperCase());
        return vehicleRepository.findAllBytransmission(enumtransmission);
    }

    @Override
    public String updatevehicle(String vin, VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle=vehicleRepository.findByvin(vin).orElseThrow(()->new RuntimeException("Vehicle not found with VIN: " + vin));
            vehicle.setBrand(vehicleRequestDto.getBrand());
            vehicle.setModel(vehicleRequestDto.getModel());
            vehicle.setManufacturingYear(vehicleRequestDto.getManufacturingYear());
            vehicle.setColor(vehicleRequestDto.getColor());
            vehicle.setVin(vehicleRequestDto.getVin());
            vehicle.setRegistrationNumber(vehicleRequestDto.getRegistrationNumber());
            vehicle.setVehicleType(vehicleRequestDto.getVehicleType());
            vehicle.setVehicleStatus(vehicleRequestDto.getVehicleStatus());
            vehicle.setVehicleCategory(vehicleRequestDto.getVehicleCategory());
            vehicle.setFuelType(vehicleRequestDto.getFuelType());
            vehicle.setTransmission(vehicleRequestDto.getTransmission());
            vehicle.setMileage(vehicleRequestDto.getMileage());
            vehicle.setPrice(vehicleRequestDto.getPrice());
            vehicle.setPurchaseDate(vehicleRequestDto.getPurchaseDate());
            vehicle.setCreatedDate(LocalDate.now());
            vehicleRepository.save(vehicle);
            return "update successfully";
        }

    @Override
    public String deletevehicle(String vin) {
        Vehicle vehicle=vehicleRepository.findByvin(vin).orElseThrow(()->new RuntimeException("Vehicle not found with VIN: " + vin));
        if(vehicle.getVehicleStatus()==VehicleStatus.DELETED){
            return "Vehicle already deleted";
        }
        vehicle.setVehicleStatus(VehicleStatus.DELETED);
        vehicleRepository.save(vehicle);
        return "Vehicle deleted successfully";
    }
}



package com.example.AutoHub.inventory.controller;

import com.example.AutoHub.inventory.dto.VehicleRequestDto;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.service.VehicleServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    @PostMapping("/add")
    public String addNewVehicle(@Valid @RequestBody VehicleRequestDto vehicleRequestDto){
        return vehicleService.addvehicle(vehicleRequestDto);
    }

    @GetMapping("/get-vehicle")
    public List<Vehicle> getAllVehicle(){
        return vehicleService.getallvehicle();
    }

    @GetMapping("/get-by-brand/{brand}")
    public List<Vehicle> getAllVehicleByBrand(@PathVariable String brand){
        return vehicleService.getallvehiclebybrand(brand);
    }

    @GetMapping("/get-by-manufacturingyear/{year}")
    public List<Vehicle> getAllVehicleByManufacturingYear(@PathVariable Integer year){
        return vehicleService.getallvehiclebymanufacturingYear(year);
    }

    @GetMapping("/get-by-vehicletype/{vehicletype}")
    public List<Vehicle> getAllVehicleByvehicletype(@PathVariable String vehicletype){
        return vehicleService.getallvehiclebytype(vehicletype);
    }

    @GetMapping("/get-by-fueltype/{fueltype}")
    public List<Vehicle> getAllVehicleByfueltype(@PathVariable String fueltype){
        return vehicleService.getallvehiclebyfueltype(fueltype);
    }


    @GetMapping("/get-by-transmission/{transmission}")
    public List<Vehicle> getAllVehicleBytransmission(@PathVariable String transmission){
        return vehicleService.getallvehiclebytransmission(transmission);
    }

    @PutMapping ("/update-vehicle/{vin}")
    public String updateVehicle(@PathVariable String vin,@Valid @RequestBody VehicleRequestDto vehicleRequestDto){
        return vehicleService.updatevehicle(vin,vehicleRequestDto);
    }

    @DeleteMapping ("/delete-vehicle/{vin}")
    public String deleteVehicle(@PathVariable String vin){
        return vehicleService.deletevehicle(vin);
    }

}

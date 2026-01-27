package com.example.AutoHub.inventory.controller;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.inventory.dto.VehicleRequestDto;
import com.example.AutoHub.inventory.dto.VehicleResponseDto;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.service.VehicleServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    @PostMapping("/add")
    public ResponseEntity<VehicleResponseDto> addNewVehicle(@Valid @RequestBody VehicleRequestDto vehicleRequestDto){
        VehicleResponseDto vehicle= vehicleService.addvehicle(vehicleRequestDto);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping("/get-vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        List<Vehicle> vehiclelist=vehicleService.getallvehicle();
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }

    @GetMapping("/get-by-brand/{brand}")
    public ResponseEntity<List<Vehicle>> getAllVehicleByBrand(@PathVariable String brand){
        List<Vehicle> vehiclelist=vehicleService.getallvehiclebybrand(brand);
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }

    @GetMapping("/get-by-manufacturingyear/{year}")
    public ResponseEntity<List<Vehicle>> getAllVehicleByManufacturingYear(@PathVariable Integer year){
        List<Vehicle> vehiclelist=vehicleService.getallvehiclebymanufacturingYear(year);
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }

    @GetMapping("/get-by-vehicletype/{vehicletype}")
    public ResponseEntity<List<Vehicle>> getAllVehicleByvehicletype(@PathVariable String vehicletype){
        List<Vehicle> vehiclelist=vehicleService.getallvehiclebytype(vehicletype);
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }

    @GetMapping("/get-by-fueltype/{fueltype}")
    public ResponseEntity<List<Vehicle>> getAllVehicleByfueltype(@PathVariable String fueltype){
        List<Vehicle> vehiclelist=vehicleService.getallvehiclebyfueltype(fueltype);
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }


    @GetMapping("/get-by-transmission/{transmission}")
    public ResponseEntity<List<Vehicle>> getAllVehicleBytransmission(@PathVariable String transmission){
        List<Vehicle> vehiclelist=vehicleService.getallvehiclebytransmission(transmission);
        if(vehiclelist.isEmpty()){
            throw new NotFoundException("No vehicles found");
        }
        return new ResponseEntity<>(vehiclelist,HttpStatus.OK);
    }

    @PutMapping ("/update-vehicle/{vin}")
    public Vehicle updateVehicle(@PathVariable String vin,@Valid @RequestBody VehicleRequestDto vehicleRequestDto) throws NotFoundException{
        return vehicleService.updatevehicle(vin,vehicleRequestDto);
    }

    @DeleteMapping ("/delete-vehicle/{vin}")
    public String deleteVehicle(@PathVariable String vin){
        return vehicleService.deletevehicle(vin);
    }


}

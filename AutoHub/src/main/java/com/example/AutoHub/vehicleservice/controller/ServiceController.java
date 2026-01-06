package com.example.AutoHub.vehicleservice.controller;

import com.example.AutoHub.vehicleservice.dto.ServiceRequestDto;
import com.example.AutoHub.vehicleservice.dto.ServiceResponseDto;
import com.example.AutoHub.vehicleservice.enumclass.ServiceStatus;
import com.example.AutoHub.vehicleservice.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceBookingService serviceBookingService;

    @PostMapping("/bookService")
    public ServiceResponseDto bookService(@RequestBody ServiceRequestDto serviceRequestDto){
        return serviceBookingService.bookService(serviceRequestDto);
    }

    @GetMapping("/serviceById/{serviceId}")
    public ServiceResponseDto getServiceRecordById(@PathVariable Long serviceId){
        return serviceBookingService.getServiceById(serviceId);
    }

    @GetMapping("/serviceHistory/{vehicleId}")
    public List<ServiceResponseDto> serviceHistory(@PathVariable Long vehicleId){
        return serviceBookingService.getServiceHistoryById(vehicleId);
    }

    @PutMapping("/updateServiceStatusInprocess/{serviceId}")
    public String updateServiceInProcess(@PathVariable Long serviceId, ServiceStatus status){
        return serviceBookingService.updateServiceStatus(serviceId,ServiceStatus.INPROCESS);
    }
    @PutMapping("/updateServiceStatusCompleted/{serviceId}")
    public String updateServiceCompleted(@PathVariable Long serviceId, ServiceStatus status){
        return serviceBookingService.updateServiceStatus(serviceId,ServiceStatus.COMPLETED);
    }
}

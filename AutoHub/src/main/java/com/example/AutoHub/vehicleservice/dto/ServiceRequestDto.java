package com.example.AutoHub.vehicleservice.dto;

import com.example.AutoHub.vehicleservice.enumclass.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ServiceRequestDto {
    private Long customerId;
    private LocalDate serviceDate;
    private ServiceType serviceType;
    private Long vehicleId;

    public ServiceRequestDto(){
        this.serviceDate=LocalDate.now();
    }
}

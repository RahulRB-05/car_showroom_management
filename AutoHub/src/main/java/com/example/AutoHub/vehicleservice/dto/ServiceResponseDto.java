package com.example.AutoHub.vehicleservice.dto;

import com.example.AutoHub.vehicleservice.enumclass.ServiceStatus;
import com.example.AutoHub.vehicleservice.enumclass.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDto {
    private Long serviceRecordId;
    private ServiceType serviceType;
    private LocalDate serviceDate;
    private ServiceStatus serviceStatus;
    private double serviceCost;
}

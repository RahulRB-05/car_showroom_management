package com.example.AutoHub.vehicleservice.service;

import com.example.AutoHub.vehicleservice.dto.ServiceRequestDto;
import com.example.AutoHub.vehicleservice.dto.ServiceResponseDto;
import com.example.AutoHub.vehicleservice.enumclass.ServiceStatus;

import java.util.List;

public interface ServiceBookingService {
    ServiceResponseDto bookService(ServiceRequestDto serviceRequestDto);
    ServiceResponseDto getServiceById(Long serviceId);
    List<ServiceResponseDto> getServiceHistoryById(Long vehicleId);
    String updateServiceStatus(Long serviceId, ServiceStatus serviceStatus);
}

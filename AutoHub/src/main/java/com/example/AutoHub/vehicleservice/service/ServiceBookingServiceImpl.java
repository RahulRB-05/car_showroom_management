package com.example.AutoHub.vehicleservice.service;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.customer.repository.CustomerRepository;
import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.vehicleservice.dto.ServiceRequestDto;
import com.example.AutoHub.vehicleservice.dto.ServiceResponseDto;
import com.example.AutoHub.vehicleservice.entity.ServiceRecord;
import com.example.AutoHub.vehicleservice.enumclass.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBookingServiceImpl implements ServiceBookingService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceResponseDto bookService(ServiceRequestDto serviceRequestDto) {
        Customer customer=customerRepository.findById(serviceRequestDto.getCustomerId()).
                orElseThrow(()->new NotFoundException("Customer Not Found"));

        Vehicle vehicle=vehicleRepository.findById(serviceRequestDto.getVehicleId()).
                orElseThrow(()->new NotFoundException("Vehicle Not Found"));

        ServiceRecord record=new ServiceRecord();
        record.setCustomer(customer);
        record.setVehicle(vehicle);
        record.setServiceDate(serviceRequestDto.getServiceDate());
        record.setServiceType(serviceRequestDto.getServiceType());
        record.setServiceStatus(ServiceStatus.BOOKED);
        record.setServiceCost(0.0);

        ServiceRecord saved=serviceRepository.save(record);

        return mapToResponse(saved);
    }

    @Override
    public ServiceResponseDto getServiceById(Long serviceId) {
        ServiceRecord record =serviceRepository.findById(serviceId).
                orElseThrow(()->new NotFoundException("ServiceRecord Not Found"));
        return mapToResponse(record);
    }

    @Override
    public List<ServiceResponseDto> getServiceHistoryById(Long vehicleId) {
        return serviceRepository.findById(vehicleId).
                stream().
                map(this::mapToResponse).
                toList();
    }

    @Override
    public String updateServiceStatus(Long serviceId, ServiceStatus serviceStatus) {
        ServiceRecord record=serviceRepository.findById(serviceId).
                orElseThrow(()->new NotFoundException("Service Report Not Found"));
        record.setServiceStatus(serviceStatus);

        serviceRepository.save(record);

        return "Service Status updated Successfully";
    }

    public ServiceResponseDto mapToResponse(ServiceRecord serviceRecord){

        ServiceResponseDto response=new ServiceResponseDto();
        response.setServiceType(serviceRecord.getServiceType());
        response.setServiceCost(serviceRecord.getServiceCost());
        response.setServiceDate(serviceRecord.getServiceDate());
        response.setServiceStatus(serviceRecord.getServiceStatus());
        response.setServiceRecordId(serviceRecord.getServiceRecordId());

        return response;
    }
}

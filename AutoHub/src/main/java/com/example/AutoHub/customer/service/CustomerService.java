package com.example.AutoHub.customer.service;

import com.example.AutoHub.customer.dto.CustomerRequestDTO;
import com.example.AutoHub.customer.dto.CustomerResponseDTO;
import com.example.AutoHub.customer.entity.Customer;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface CustomerService {


    String deleteCustomer(Long id);


    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO updateCustomer(Long id,CustomerRequestDTO dto);

    CustomerResponseDTO getCustomersById(Long id);

    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);
}

package com.example.AutoHub.customer.controller;

import com.example.AutoHub.customer.dto.CustomerRequestDTO;
import com.example.AutoHub.customer.dto.CustomerResponseDTO;
import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class  CustomerController {


    @Autowired
    private CustomerService customerService;

    //create
    @PostMapping("/create")
    public CustomerResponseDTO createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.createCustomer(customerRequestDTO);

    }
    //read by id
    @GetMapping("/customerById/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomersById(id);
    }

    //read All
    @GetMapping("/allCustomers")
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update/{id}")
    public CustomerResponseDTO updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO customerRequestDTO) {

        return customerService.updateCustomer(id, customerRequestDTO);
    }
    @DeleteMapping("remove/{id}")
    public String deleteCustomer(@PathVariable Long id){
       return customerService.deleteCustomer(id);
    }

}

package com.example.AutoHub.customer.service;

import com.example.AutoHub.customer.dto.CustomerRequestDTO;
import com.example.AutoHub.customer.dto.CustomerResponseDTO;
import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.customer.repository.CustomerRepository;
import com.example.AutoHub.exception.DuplicateEntryException;
import com.example.AutoHub.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{


    private CustomerRepository customerRepository;


    @Override
    public String deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with Id:"+id));
        customerRepository.delete(existingCustomer);
        return ("Customer deleted successfully with id: "+ id);

    }

    @Override
    public  List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers =customerRepository.findAll();
        List<CustomerResponseDTO> responseList = new ArrayList<>();

        for (Customer customer : customers){
            responseList.add(mapToResponse(customer));
        }

        return responseList;

    }


    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer  existingCustomer =customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: "+ id));
        existingCustomer.setCustomerName(dto.getName());
        existingCustomer.setCustomerEmail(dto.getEmail());
        existingCustomer.setCustomerPhone(dto.getPhone());
        existingCustomer.setCustomerAddress(dto.getAddress());


        return mapToResponse(existingCustomer);
    }



    @Override
    public CustomerResponseDTO getCustomersById(Long id) {
       Customer customer= customerRepository.findById(id)
                .orElseThrow(() ->new NotFoundException("Customer Not Found"));

        return  mapToResponse(customer);
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        customerRepository.findByCustomerEmail(dto.getEmail())
                .ifPresent(c -> {
                    throw new DuplicateEntryException("Customer already exists");
                });
        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setCustomerName(dto.getName());
        customer.setCustomerEmail(dto.getEmail());
        customer.setCustomerPhone(dto.getPhone());
        customer.setCustomerAddress(dto.getAddress());

        Customer savedCustomer =customerRepository.save(customer);

        return mapToResponse(customer);

    }
     public CustomerResponseDTO mapToResponse(Customer customer){
        CustomerResponseDTO response =new CustomerResponseDTO();
        response.setId(customer.getCustomerId());
        response.setName(customer.getCustomerName());
        response.setEmail(customer.getCustomerEmail());
        response.setPhone(customer.getCustomerPhone());
        response.setAddress(customer.getCustomerAddress());

        return response;
     }


}

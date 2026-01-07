package com.example.AutoHub.customer.repository;

import com.example.AutoHub.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer,Long>{
}

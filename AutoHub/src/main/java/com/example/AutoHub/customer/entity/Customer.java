package com.example.AutoHub.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private Long customerId;
}

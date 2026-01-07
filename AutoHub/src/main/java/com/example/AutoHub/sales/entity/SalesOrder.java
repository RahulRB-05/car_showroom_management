package com.example.AutoHub.sales.entity;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.sales.enumclass.SalesStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sales_orders")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesOrderId;

    private double totalAmount;
    private LocalDate salesDate;

    @Enumerated(EnumType.STRING)
    private SalesStatus status;

    @OneToOne(mappedBy = "salesOrder")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}

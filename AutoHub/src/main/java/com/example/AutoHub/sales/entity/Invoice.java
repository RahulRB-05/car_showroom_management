package com.example.AutoHub.sales.entity;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.sales.enumclass.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private double invoiceAmount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne(mappedBy = "invoice")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

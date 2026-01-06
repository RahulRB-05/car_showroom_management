package com.example.AutoHub.sales.dto;

import com.example.AutoHub.sales.enumclass.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentDto {
    private Long salesOrderId;
    private double amount;
    private PaymentType paymentMethod;
    private LocalDate orderDate;

    public PaymentDto(){
        this.orderDate=LocalDate.now();
    }
}

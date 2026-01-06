package com.example.AutoHub.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QuoteDto {

    private Long customerId;
    private Long vehicleId;
    private double quotedPrice;
    private LocalDate salesDate;

    public QuoteDto(){
        this.salesDate= LocalDate.now();
    }
}

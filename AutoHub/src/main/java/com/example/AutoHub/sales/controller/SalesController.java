package com.example.AutoHub.sales.controller;

import com.example.AutoHub.sales.dto.PaymentDto;
import com.example.AutoHub.sales.dto.QuoteDto;
import com.example.AutoHub.sales.entity.Invoice;
import com.example.AutoHub.sales.entity.SalesOrder;
import com.example.AutoHub.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @PostMapping("/quote")
    public SalesOrder generateQuote(@RequestBody QuoteDto quoteDto){
        return salesService.generateQuote(quoteDto);
    }

    @PutMapping("/order/{id}")
    public SalesOrder createOrder(@PathVariable Long id){
        return salesService.createSalesOrder(id);
    }

    @PutMapping("/cancelOrder/{id}")
    public SalesOrder cancelOrder(@PathVariable Long id){
        return salesService.cancelSalesOrder(id);
    }

    @PutMapping("/payment")
    public String processPayment(@RequestBody PaymentDto paymentDto){
        return salesService.processPayment(paymentDto);
    }

    @PostMapping("/invoice/{id}")
    public Invoice generateInvoice(@PathVariable Long id){
        return salesService.generateInvoice(id);
    }

    @GetMapping("/getInvoice/{id}")
    public Invoice getInvoice(@PathVariable Long id){return salesService.getInvoiceById(id);}

    @GetMapping("/getSales/{id}")
    public SalesOrder getSales(@PathVariable Long id){
        return salesService.getSales(id);
    }
}

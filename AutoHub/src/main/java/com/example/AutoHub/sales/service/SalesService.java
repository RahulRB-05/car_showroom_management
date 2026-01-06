package com.example.AutoHub.sales.service;

import com.example.AutoHub.sales.dto.PaymentDto;
import com.example.AutoHub.sales.dto.QuoteDto;
import com.example.AutoHub.sales.entity.Invoice;
import com.example.AutoHub.sales.entity.SalesOrder;

public interface SalesService {
    SalesOrder generateQuote(QuoteDto quoteDto);
    SalesOrder createSalesOrder(Long salesOrderId);
    Invoice generateInvoice(Long salesOrderId);
    String processPayment(PaymentDto paymentDto);
    SalesOrder getSales(Long salesOrderId);
}

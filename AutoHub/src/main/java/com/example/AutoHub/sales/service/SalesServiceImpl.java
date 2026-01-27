package com.example.AutoHub.sales.service;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.customer.repository.CustomerRepository;
import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.sales.dto.PaymentDto;
import com.example.AutoHub.sales.dto.QuoteDto;
import com.example.AutoHub.sales.entity.Invoice;
import com.example.AutoHub.sales.entity.SalesOrder;
import com.example.AutoHub.sales.enumclass.SalesStatus;
import com.example.AutoHub.sales.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SalesRepository salesRepo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public SalesOrder generateQuote(QuoteDto quoteDto) {

        Vehicle vehicle=vehicleRepository.findById(quoteDto.getVehicleId()).orElseThrow(()->new NotFoundException("Vehicle not Found"));
        Customer customer=customerRepository.findById(quoteDto.getCustomerId()).orElseThrow(()->new NotFoundException("Customer not Found"));
        SalesOrder order=new SalesOrder();
        order.setCustomer(customer);
        order.setVehicle(vehicle);
        order.setTotalAmount(quoteDto.getQuotedPrice());
        order.setStatus(SalesStatus.QUOTED);

        return salesRepo.save(order);
    }

    @Override
    public SalesOrder createSalesOrder(Long salesOrderId) {
        SalesOrder order=salesRepo.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));
        order.setStatus(SalesStatus.ORDERED);

        return salesRepo.save(order);
    }

    @Override
    public Invoice generateInvoice(Long salesOrderId) {
        SalesOrder order=salesRepo.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));
        Invoice invoice=new Invoice();
        invoice.setInvoiceNumber("IN-"+System.currentTimeMillis());
        invoice.setInvoiceAmount(order.getTotalAmount());
        invoice.setSalesOrder(order);

        order.setInvoice(invoice);

        salesRepo.save(order);
        return invoice ;
    }

    @Override
    public String processPayment(PaymentDto paymentDto) {
        SalesOrder order=salesRepo.findById(paymentDto.getSalesOrderId()).orElseThrow(()->new NotFoundException("Sales order not found"));
        order.setStatus(SalesStatus.PAID);
        salesRepo.save(order);

        return "Payment Successful via - "+paymentDto.getPaymentMethod().toString();
    }

    @Override
    public SalesOrder getSales(Long salesOrderId) {
        return salesRepo.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));
    }
}

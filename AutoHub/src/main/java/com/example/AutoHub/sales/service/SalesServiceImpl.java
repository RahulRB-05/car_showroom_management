package com.example.AutoHub.sales.service;

import com.example.AutoHub.customer.entity.Customer;
import com.example.AutoHub.customer.repository.CustomerRepository;
import com.example.AutoHub.exception.ConcurrencyException;
import com.example.AutoHub.exception.InvalidInputException;
import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.enumclass.VehicleStatus;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.sales.dto.PaymentDto;
import com.example.AutoHub.sales.dto.QuoteDto;
import com.example.AutoHub.sales.entity.Invoice;
import com.example.AutoHub.sales.entity.SalesOrder;
import com.example.AutoHub.sales.enumclass.SalesStatus;
import com.example.AutoHub.sales.repository.InvoiceRepository;
import com.example.AutoHub.sales.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SalesServiceImpl implements SalesService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SalesRepository salesRepository;

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
        order.setTotalAmount(vehicle.getPrice());
        order.setStatus(SalesStatus.QUOTED);
        order.setSalesDate(LocalDate.now());

        return salesRepository.save(order);
    }

    @Override
    public SalesOrder createSalesOrder(Long salesOrderId) {
        SalesOrder order= salesRepository.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));
        Vehicle vehicle=vehicleRepository.findById(order.getVehicle().getVehicleId()).orElseThrow(()-> new NotFoundException("Vehicle not found"));

        if(order.getStatus().toString().equalsIgnoreCase("CANCELLED")){
            throw new InvalidInputException("Already cancelled create a new quote...");
        }

        vehicle.setVehicleStatus(VehicleStatus.RESERVED);
        order.setStatus(SalesStatus.ORDERED);

        vehicleRepository.save(vehicle);
        return salesRepository.save(order);
    }

    @Override
    public SalesOrder cancelSalesOrder(Long salesOrderId){
        SalesOrder order= salesRepository.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales Order Not Found..."));
        Vehicle vehicle=vehicleRepository.findById(order.getVehicle().getVehicleId()).orElseThrow(()->new NotFoundException(("Vehicle not found...")));

        if(order.getStatus().toString().equalsIgnoreCase("CANCELLED")){
            throw new InvalidInputException("Already cancelled create a new quote...");
        }

        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        order.setStatus(SalesStatus.CANCELLED);

        vehicleRepository.save(vehicle);
        return salesRepository.save(order);
    }

    @Override
    public String processPayment(PaymentDto paymentDto) {
        SalesOrder order= salesRepository.findById(paymentDto.getSalesOrderId()).orElseThrow(()->new NotFoundException("Sales order not found"));
        Vehicle vehicle=vehicleRepository.findById(order.getVehicle().getVehicleId()).orElseThrow(()->new NotFoundException("Vehicle not found..."));

        if(order.getStatus().toString().equalsIgnoreCase("CANCELLED")){
            throw new InvalidInputException("Already cancelled create a new quote...");
        }

        if(order.getStatus()==SalesStatus.PAID){
            throw new ConcurrencyException("Already Paid...");
        }

        order.setStatus(SalesStatus.PAID);
        order.setType(paymentDto.getPaymentMethod());

        vehicle.setVehicleStatus(VehicleStatus.SOLD);

        vehicleRepository.save(vehicle);
        salesRepository.save(order);

        return "Payment Successful via - "+paymentDto.getPaymentMethod().toString();
    }

    @Override
    @Transactional
    public Invoice generateInvoice(Long salesOrderId) {
        SalesOrder order= salesRepository.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));

        if(order.getStatus().toString().equalsIgnoreCase("CANCELLED")){
            throw new ConcurrencyException("Already cancelled cannot create invoice...");
        }
        if(order.getInvoice()!=null){
            throw new ConcurrencyException("Invoice already created...");
        }

        Invoice invoice=new Invoice();
        invoice.setInvoiceNumber("IN-"+System.currentTimeMillis());
        invoice.setInvoiceAmount(order.getTotalAmount());
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setPaymentType(order.getType());
        invoice.setCustomer(order.getCustomer());

        invoice.setSalesOrder(order);

        order.setInvoice(invoice);
        salesRepository.save(order);
        return invoice ;
    }

    @Override
    public Invoice getInvoiceById(Long customerId) {
        Invoice invoice=invoiceRepository.findById(customerId).orElseThrow(()->new NotFoundException("Invoice not found"));
        return invoice;
    }

    @Override
    public SalesOrder getSales(Long salesOrderId) {
        return salesRepository.findById(salesOrderId).orElseThrow(()->new NotFoundException("Sales order not found"));
    }
}

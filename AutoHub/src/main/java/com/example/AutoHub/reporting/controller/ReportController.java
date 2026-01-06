package com.example.AutoHub.reporting.controller;

import com.example.AutoHub.reporting.dto.ReportResponseDto;
import com.example.AutoHub.reporting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/sales")
    public ReportResponseDto salesReport(){
        return reportService.generateSalesReport();
    }

    @GetMapping("/service")
    public ReportResponseDto serviceReport(){
        return reportService.generateServiceReport();
    }

    @GetMapping("/inventory")
    public ReportResponseDto inventoryReport(){
        return reportService.generateInventoryReport();
    }
}

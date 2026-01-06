package com.example.AutoHub.reporting.service;

import com.example.AutoHub.reporting.async.ReportAsyncService;
import com.example.AutoHub.reporting.dto.ReportResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportAsyncService reportAsyncService;

    @Override
    public ReportResponseDto generateSalesReport() {
        reportAsyncService.generateSalesReportAsync();
        return response("Sales Report");
    }

    @Override
    public ReportResponseDto generateServiceReport() {
        reportAsyncService.generateServiceReportAsync();
        return response("Service Report");
    }

    @Override
    public ReportResponseDto generateInventoryReport() {
        reportAsyncService.generateServiceReportAsync();
        return response("Inventory Report");
    }

    public ReportResponseDto response(String name){
        ReportResponseDto report=new ReportResponseDto();
        report.setReportName(name);
        report.setStatus("PROCESSING");
        report.setFilePath("Will be available after generation...");

        return report;
    }
}

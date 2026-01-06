package com.example.AutoHub.reporting.service;

import com.example.AutoHub.reporting.dto.ReportResponseDto;

public interface ReportService {
    ReportResponseDto generateSalesReport();
    ReportResponseDto generateServiceReport();
    ReportResponseDto generateInventoryReport();
}

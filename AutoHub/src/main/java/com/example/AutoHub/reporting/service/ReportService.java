package com.example.AutoHub.reporting.service;

import java.io.File;

public interface ReportService {
    File downloadSalesReport(String format);
    File downloadServiceReport(String format);
    File downloadInventoryReport(String format);
}

package com.example.AutoHub.reporting.service;

import java.io.File;

public interface ReportService {
    File downloadSalesReport();
    File downloadServiceReport();
    File downloadInventoryReport();
}

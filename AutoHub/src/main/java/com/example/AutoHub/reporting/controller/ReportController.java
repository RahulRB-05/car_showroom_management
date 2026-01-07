package com.example.AutoHub.reporting.controller;

import com.example.AutoHub.reporting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping(value = "/sales",produces = "application/octet-stream")
    public Resource salesReport(String format){
        File file =reportService.downloadSalesReport(format);
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/service",produces = "application/octet-stream")
    public Resource serviceReport(String format){
        File file=reportService.downloadServiceReport(format);
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/inventory",produces = "application/octet-stream")
    public Resource inventoryReport(String format){
        File file=reportService.downloadInventoryReport(format);
        return new FileSystemResource(file);
    }
}

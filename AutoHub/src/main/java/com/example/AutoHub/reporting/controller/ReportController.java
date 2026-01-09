package com.example.AutoHub.reporting.controller;

import com.example.AutoHub.reporting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/sales",produces = "application/octet-stream")
    public Resource salesReport(){
        File file =reportService.downloadSalesReport();
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/service",produces = "application/octet-stream")
    public Resource serviceReport(){
        File file=reportService.downloadServiceReport();
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/inventory",produces = "application/octet-stream")
    public Resource inventoryReport(){
        File file=reportService.downloadInventoryReport();
        return new FileSystemResource(file);
    }
}

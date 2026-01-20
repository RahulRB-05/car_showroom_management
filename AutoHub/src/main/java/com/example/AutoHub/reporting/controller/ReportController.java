package com.example.AutoHub.reporting.controller;

import com.example.AutoHub.reporting.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/sales/{format}",produces = "application/octet-stream")
    public Resource salesReport(@PathVariable String format){
        File file =reportService.downloadSalesReport(format);
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/service/{format}",produces = "application/octet-stream")
    public Resource serviceReport(@PathVariable String format){
        File file=reportService.downloadServiceReport(format);
        return new FileSystemResource(file);
    }

    @GetMapping(value = "/inventory/{format}",produces = "application/octet-stream")
    public Resource inventoryReport(@PathVariable String format){
        File file=reportService.downloadInventoryReport(format);
        return new FileSystemResource(file);
    }
}

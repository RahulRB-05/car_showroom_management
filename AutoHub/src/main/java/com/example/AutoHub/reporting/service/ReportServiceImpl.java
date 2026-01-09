package com.example.AutoHub.reporting.service;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.reporting.util.CsvReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private CsvReportUtil csvReportUtil;

    @Override
    public File downloadSalesReport() {
        File file = csvReportUtil.generateSalesReport();

        if(file==null || !file.exists()){
            throw new NotFoundException("Sales report generation Failed");
        }
        return file;
    }

    @Override
    public File downloadServiceReport() {
        File file= csvReportUtil.generateServiceReport();

        if(file==null || !file.exists()){
            throw new NotFoundException("Service report generation failed");
        }
        return file;
    }

    @Override
    public File downloadInventoryReport() {
        File file = csvReportUtil.generateInventoryReport();
        if(file==null || !file.exists()){
            throw new NotFoundException("Inventory report generation failed");
        }
        return file;
    }
}

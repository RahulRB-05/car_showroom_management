package com.example.AutoHub.reporting.service;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.reporting.util.JasperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private JasperUtil jasperUtil;

    @Override
    public File downloadSalesReport(String format) {
        File file =jasperUtil.generateSalesReport(format);

        if(file==null || !file.exists()){
            throw new NotFoundException("Sales report generation Failed");
        }

        return file;
    }

    @Override
    public File downloadServiceReport(String format) {
        File file=jasperUtil.generateServiceReport(format);

        if(file==null || !file.exists()){
            throw new NotFoundException("Service report generation failed");
        }

        return file;
    }

    @Override
    public File downloadInventoryReport(String format) {
        File file =jasperUtil.generateInventoryReport(format);
        if(file==null || !file.exists()){
            throw new NotFoundException("Inventory report generation failed");
        }

        return file;
    }
}

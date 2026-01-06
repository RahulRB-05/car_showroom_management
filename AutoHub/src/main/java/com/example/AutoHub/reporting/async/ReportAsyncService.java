package com.example.AutoHub.reporting.async;

import com.example.AutoHub.reporting.util.ChartUtil;
import com.example.AutoHub.reporting.util.JasperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReportAsyncService {
    @Autowired
    private ChartUtil chartUtil;

    @Autowired
    private JasperUtil jasperUtil;

    @Async
    public void generateSalesReportAsync(){
        chartUtil.generateSalesChart();
        jasperUtil.generateSalesReport();
    }

    @Async
    public void generateServiceReportAsync(){
        chartUtil.generateServiceChart();
        jasperUtil.generateServiceReport();
    }

    @Async
    public void generateInventoryReportAsync(){
        chartUtil.generateInventoryChart();
        jasperUtil.generateInventoryReport();
    }
}

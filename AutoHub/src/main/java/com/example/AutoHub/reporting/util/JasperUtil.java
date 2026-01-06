package com.example.AutoHub.reporting.util;

import org.springframework.stereotype.Component;

@Component
public class JasperUtil {

    public void generateSalesReport(){
        System.out.println("Generating Sales jasper report...");
    }

    public void generateServiceReport(){
        System.out.println("Generating Service jasper report...");
    }

    public void generateInventoryReport(){
        System.out.println("Generating Inventory jasper report...");
    }
}

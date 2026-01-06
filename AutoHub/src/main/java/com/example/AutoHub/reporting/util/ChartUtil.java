package com.example.AutoHub.reporting.util;

import org.springframework.stereotype.Component;

@Component
public class ChartUtil {

    public void generateSalesChart(){
        System.out.println("Generating Sales Chart...");
    }

    public void generateServiceChart(){
        System.out.println("Generating Service Chart...");
    }

    public void generateInventoryChart(){
        System.out.println("Generating Inventory Chart...");
    }
}

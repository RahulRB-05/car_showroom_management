package com.example.AutoHub.notification.service;

import com.example.AutoHub.notification.entity.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    @Autowired
    private EmailService emailService;

    public void checkLowStock(VehicleInventory inventory){
        if(inventory.getStockCount()<= inventory.getStockCountThreshold() && !inventory.isLowStockAlertSent()){
            emailService.sendLowStockMail(inventory);
        }
    }
}

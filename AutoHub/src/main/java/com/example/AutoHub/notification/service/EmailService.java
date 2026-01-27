package com.example.AutoHub.notification.service;

import com.example.AutoHub.notification.entity.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${showroom.admin.email}")
    private String adminMail;

    public void sendLowStockMail(VehicleInventory inventory){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(adminMail);
        message.setSubject("Low Stock - "+inventory.getBrand()+" "+inventory.getModel());

        message.setText(
                "Low Stock Warning \n\n"+
                        "Brand : "+inventory.getBrand()+"\n"+
                        "Model : "+inventory.getModel()+"\n"+
                        "Current Stock : "+inventory.getStockCount()+"\n"+
                        "Please replenish the stock immediately \n\n"+
                        "Showroom Road Map System"
        );

        mailSender.send(message);
    }
}

package com.example.AutoHub.notification.service;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.notification.entity.VehicleInventory;
import com.example.AutoHub.notification.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    AlertService alertService;

    public void reduceStock(String brand,String model){
        VehicleInventory inventory=inventoryRepository.findInventoryByBrandAndModel(brand,model)
                .orElseThrow(()->new NotFoundException("Inventory not Found!!!"));

        if(inventory.getStockCount()<=0){
            throw new NotFoundException("Stock not found!!!");
        }

        inventory.setStockCount(inventory.getStockCount()-1);

        alertService.checkLowStock(inventory);

        inventoryRepository.save(inventory);
    }

    public void increaseStock(String brand,String model){

        VehicleInventory inventory=inventoryRepository.findInventoryByBrandAndModel(brand, model)
                .orElseGet(()-> {
            VehicleInventory newInventory = new VehicleInventory();
            newInventory.setStockCount(1);
            newInventory.setBrand(brand);
            newInventory.setModel(model);
            return newInventory;
        }
    );
        inventory.setStockCount(inventory.getStockCount()+1);
        inventoryRepository.save(inventory);
    }
}

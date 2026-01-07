package com.example.AutoHub.inventory.enumclass;

public enum VehicleStatus {

    AVAILABLE,     // Vehicle is in stock and ready for sale
    SOLD,          // Vehicle has been sold to a customer
    IN_SERVICE,    // Vehicle is currently under maintenance/service
    RESERVED,      // Vehicle is booked but not yet delivered
    DELETED

}

package com.example.AutoHub.reporting.util;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.reporting.constant.ReportConstants;
import com.example.AutoHub.sales.entity.SalesOrder;
import com.example.AutoHub.sales.repository.SalesRepository;
import com.example.AutoHub.vehicleservice.entity.ServiceRecord;
import com.example.AutoHub.vehicleservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

@Component
public class CsvReportUtil {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public File generateSalesReport(){
        File file=new File(ReportConstants.REPORT_PATH);
        return file;
    }

    public File generateServiceReport(){
        try {
            createDir();
            File file = new File(ReportConstants.REPORT_PATH+ReportConstants.SERVICE_REPORT);
            PrintWriter writer=new PrintWriter(file);

            writer.println(ReportConstants.SERVICE_REPORT);

            List<ServiceRecord> services=serviceRepository.findAll();

            for(ServiceRecord service : services){
                writer.println("VIN             : "+service.getVehicle().getVin());
                writer.println("Vehicle         : "+service.getVehicle().getModel());
                writer.println("Customer        : "+service.getCustomer().getCustomerId());
                writer.println("Customer Id     : "+service.getCustomer().getCustomerId());
                writer.println("Service Status  : "+service.getServiceStatus());
                writer.println("Charges         : "+service.getServiceCost());
            }
            writer.close();
            return file;
        }catch(Exception e){
            throw new NotFoundException("Service report generation failed...");
        }
    }
    public File generateInventoryReport(){
       try{
           createDir();
           File file=new File(ReportConstants.REPORT_PATH+ReportConstants.INVENTORY_REPORT);
           PrintWriter writer=new PrintWriter(file);
           writer.println(ReportConstants.INVENTORY_REPORT);

           List<Vehicle> vehicles=vehicleRepository.findAll();
           for(Vehicle vehicle : vehicles){

               writer.println("Brand        : "+vehicle.getBrand());
               writer.println("Model        : "+vehicle.getModel());
               writer.println("Year         : "+ vehicle.getManufacturingYear());
               writer.println("VIN          : "+vehicle.getVin());
               writer.println("FuelType     : "+vehicle.getFuelType());
               writer.println("VehicleType  : "+vehicle.getVehicleType());
               writer.println("Status       : "+vehicle.getVehicleStatus());

               writer.close();
           }
           return file;
       }catch(Exception e){
           throw new NotFoundException("Inventory report generation failed...");
       }
    }

    public void createDir(){
        File dir=new File(ReportConstants.REPORT_PATH);
        dir.mkdirs();
    }
}

package com.example.AutoHub.reporting.util;

import com.example.AutoHub.exception.ReportGenerationFailedException;
import com.example.AutoHub.inventory.entity.Vehicle;
import com.example.AutoHub.inventory.repository.VehicleRepository;
import com.example.AutoHub.reporting.constant.ReportConstants;
import com.example.AutoHub.sales.entity.SalesOrder;
import com.example.AutoHub.sales.repository.SalesRepository;
import com.example.AutoHub.vehicleservice.entity.ServiceRecord;
import com.example.AutoHub.vehicleservice.repository.ServiceRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CsvReportUtil {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public File generateSalesReport(String format){

        if(format.equalsIgnoreCase("pdf")){
            try{
                createDir();
                File file=new File(ReportConstants.REPORT_PATH+ReportConstants.SALES_REPORT+".pdf");
                PdfWriter writer=new PdfWriter(file);
                PdfDocument pdf=new PdfDocument(writer);
                Document document=new Document(pdf);

                document.add(new Paragraph(ReportConstants.SALES_REPORT+" - "+LocalDateTime.now()));

                List<SalesOrder> sales=salesRepository.findAll();
                int count = 1;
                for(SalesOrder order:sales){
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph(count+":-"));
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph("SalesOrderId        : " + order.getSalesOrderId()));
                    document.add(new Paragraph("Sale Price          : " + order.getTotalAmount()));
                    document.add(new Paragraph("Customer Name       : " + order.getCustomer().getCustomerName()));
                    document.add(new Paragraph("Vehicle             : " + order.getVehicle().getModel()));
                    document.add(new Paragraph("VIN                 : " + order.getVehicle().getVin()));
                    document.add(new Paragraph("Sale Status         : " + order.getStatus()));
                    document.add(new Paragraph("----------------------------------------"));
                    count++;
                }
                document.close();
                return file;
            }catch(Exception e){
                throw new ReportGenerationFailedException("Sales report pdf generation failed...");
            }
        }

        else if (format.equalsIgnoreCase("csv")) {
                createDir();
                File file = new File(ReportConstants.REPORT_PATH + ReportConstants.SALES_REPORT + ".csv");

                try(PrintWriter writer=new PrintWriter(file)) {

                    writer.println(ReportConstants.SALES_REPORT + " - " + LocalDateTime.now());
                    writer.println("SlNo,SalesOrderId,Sale Price,Customer Name,Vehicle,VIN,Sale Status");

                    List<SalesOrder> sales = salesRepository.findAll();
                    int count = 1;
                    for (SalesOrder order : sales) {
                        writer.println(
                                count + "," +
                                        order.getSalesOrderId() + "," +
                                        order.getTotalAmount() + "," +
                                        order.getCustomer().getCustomerName() + "," +
                                        order.getVehicle().getModel() + "," +
                                        order.getVehicle().getVin() + "," +
                                        order.getStatus()
                        );
                        count++;
                    }
                    writer.close();
                    return file;
            }catch (Exception e){
                throw new ReportGenerationFailedException("Sales report csv generation failed...");
            }
        }

        else {
            try {
                File file = new File(ReportConstants.REPORT_PATH+ReportConstants.SALES_REPORT+".txt");
                PrintWriter writer = new PrintWriter(file);

                writer.println(ReportConstants.SALES_REPORT);
                writer.println();

                List<SalesOrder> sales = salesRepository.findAll();
                int count=1;
                for (SalesOrder order : sales) {
                    writer.println("----------------------------------------");
                    writer.println(count+":-");
                    writer.println("----------------------------------------");
                    writer.println("SalesOrderId        : " + order.getSalesOrderId());
                    writer.println("Sale Price          : " + order.getTotalAmount());
                    writer.println("Customer Name       : " + order.getCustomer().getCustomerName());
                    writer.println("Vehicle             : " + order.getVehicle().getModel());
                    writer.println("VIN                 : " + order.getVehicle().getVin());
                    writer.println("Sale Status         : " + order.getStatus());
                    writer.println("----------------------------------------");
                }
                writer.close();
                return file;
            } catch (Exception e) {
                throw new ReportGenerationFailedException("Sales report txt generation failed...");
            }
        }
    }

    public File generateServiceReport(String format){
        if(format.equalsIgnoreCase("pdf")){
            try{
                createDir();
                File file=new File(ReportConstants.REPORT_PATH+ReportConstants.SERVICE_REPORT+".pdf");
                PdfWriter writer=new PdfWriter(file);
                PdfDocument pdf=new PdfDocument(writer);
                Document document=new Document(pdf);

                document.add(new Paragraph(ReportConstants.SERVICE_REPORT+" - "+LocalDateTime.now()));

                List<ServiceRecord> services=serviceRepository.findAll();
                int count=1;
                for(ServiceRecord service:services){
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph(count+":-"));
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph("VIN             : " + service.getVehicle().getVin()));
                    document.add(new Paragraph("Vehicle         : " + service.getVehicle().getModel()));
                    document.add(new Paragraph("Customer        : " + service.getCustomer().getCustomerName()));
                    document.add(new Paragraph("Customer Id     : " + service.getCustomer().getCustomerId()));
                    document.add(new Paragraph("Date            : " + service.getServiceDate()));
                    document.add(new Paragraph("Service Status  : " + service.getServiceStatus()));
                    document.add(new Paragraph("Charges         : " + service.getServiceCost()));
                    document.add(new Paragraph("----------------------------------------"));
                    count++;
                }
                document.close();
                return file;
            }
            catch (Exception e){
                throw new ReportGenerationFailedException("Service report pdf generation failed...");
            }
        }

        else if (format.equalsIgnoreCase("csv")) {
                createDir();
                File file=new File(ReportConstants.REPORT_PATH+ReportConstants.SERVICE_REPORT+".csv");

               try(PrintWriter writer=new PrintWriter(file)){

                writer.println(ReportConstants.SERVICE_REPORT+" - "+LocalDateTime.now());
                writer.println("SlNo,VIN,Vehicle,Customer,CustomerId,Date,ServiceStatus,Charges");

                List<ServiceRecord> services=serviceRepository.findAll();
                int count=1;
                for(ServiceRecord service:services){
                    writer.println(
                                    count+","+
                                    service.getVehicle().getVin()+","+
                                    service.getVehicle().getModel()+","+
                                    service.getCustomer().getCustomerName()+","+
                                    service.getCustomer().getCustomerId()+","+
                                    service.getServiceDate()+","+
                                    service.getServiceStatus()+","+
                                    service.getServiceCost()
                    );
                    count++;
                }
                writer.close();
                return file;
            }
            catch(Exception e){
                throw new ReportGenerationFailedException("Service report csv generation failed...");
            }
        }

        else {
            try {
                createDir();
                File file = new File(ReportConstants.REPORT_PATH + ReportConstants.SERVICE_REPORT+".txt");
                PrintWriter writer = new PrintWriter(file);

                writer.println(ReportConstants.SERVICE_REPORT + " : " + LocalDateTime.now());

                List<ServiceRecord> services = serviceRepository.findAll();
                int count=1;
                for (ServiceRecord service : services) {
                    writer.println("----------------------------------------");
                    writer.println(count+":-");
                    writer.println("----------------------------------------");
                    writer.println("VIN             : " + service.getVehicle().getVin());
                    writer.println("Vehicle         : " + service.getVehicle().getModel());
                    writer.println("Customer        : " + service.getCustomer().getCustomerName());
                    writer.println("Customer Id     : " + service.getCustomer().getCustomerId());
                    writer.println("Date            : " + service.getServiceDate());
                    writer.println("Service Status  : " + service.getServiceStatus());
                    writer.println("Charges         : " + service.getServiceCost());
                    writer.println("----------------------------------------");
                    count++;
                }
                writer.close();
                return file;
            } catch (Exception e) {
                throw new ReportGenerationFailedException("Service report txt generation failed...");
            }
        }
    }
    public File generateInventoryReport(String format){
        if(format.equalsIgnoreCase("pdf")){
            try{
                createDir();
                File file=new File(ReportConstants.REPORT_PATH+ReportConstants.INVENTORY_REPORT+".pdf");
                PdfWriter writer=new PdfWriter(file);
                PdfDocument pdf=new PdfDocument(writer);
                Document document=new Document(pdf);

                document.add(new Paragraph(ReportConstants.INVENTORY_REPORT+" - "+LocalDateTime.now()));
                document.add(new Paragraph());

                List<Vehicle> vehicles=vehicleRepository.findAll();
                int count=1;
                for(Vehicle vehicle:vehicles){
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph(count+":-"));
                    document.add(new Paragraph("----------------------------------------"));
                    document.add(new Paragraph("Brand        : " + vehicle.getBrand()));
                    document.add(new Paragraph("Model        : " + vehicle.getModel()));
                    document.add(new Paragraph("Year         : " + vehicle.getManufacturingYear()));
                    document.add(new Paragraph("VIN          : " + vehicle.getVin()));
                    document.add(new Paragraph("FuelType     : " + vehicle.getFuelType()));
                    document.add(new Paragraph("VehicleType  : " + vehicle.getVehicleType()));
                    document.add(new Paragraph("Status       : " + vehicle.getVehicleStatus()));
                    document.add(new Paragraph("----------------------------------------"));
                    count++;
                }
                document.close();
                return file;
            }catch (Exception e){
                throw new ReportGenerationFailedException("Inventory report pdf generation failed...");
            }
        }
        else if(format.equalsIgnoreCase("csv")){
                createDir();
                File file=new File(ReportConstants.REPORT_PATH+ReportConstants.INVENTORY_REPORT+".csv");

                try(PrintWriter writer=new PrintWriter(file)){

                writer.println(ReportConstants.INVENTORY_REPORT+" - "+LocalDateTime.now());
                writer.println("SlNo,Brand,Model,Year,VIN,FuelType,VehicleType,Status");

                List<Vehicle> vehicles=vehicleRepository.findAll();
                int count=1;

                for(Vehicle vehicle:vehicles){
                    writer.println(
                            count+","+
                                    vehicle.getBrand()+","+
                                    vehicle.getModel()+","+
                                    vehicle.getManufacturingYear()+","+
                                    vehicle.getVin()+","+
                                    vehicle.getFuelType()+","+
                                    vehicle.getVehicleType()+","+
                                    vehicle.getVehicleStatus()
                    );
                    count++;
                }
                writer.close();
                return file;
            }
            catch(Exception e){
                e.printStackTrace();
                throw new ReportGenerationFailedException("Inventory report csv generation failed");
            }
        }
        else {
            try {
                createDir();
                File file = new File(ReportConstants.REPORT_PATH + ReportConstants.INVENTORY_REPORT+".txt");
                PrintWriter writer = new PrintWriter(file);
                writer.println(ReportConstants.INVENTORY_REPORT + " : " + LocalDateTime.now());

                List<Vehicle> vehicles = vehicleRepository.findAll();
                int count=1 ;
                for (Vehicle vehicle : vehicles) {
                    writer.println("----------------------------------------");
                    writer.println(count+":-");
                    writer.println("----------------------------------------");
                    writer.println("Brand        : " + vehicle.getBrand());
                    writer.println("Model        : " + vehicle.getModel());
                    writer.println("Year         : " + vehicle.getManufacturingYear());
                    writer.println("VIN          : " + vehicle.getVin());
                    writer.println("FuelType     : " + vehicle.getFuelType());
                    writer.println("VehicleType  : " + vehicle.getVehicleType());
                    writer.println("Status       : " + vehicle.getVehicleStatus());
                    writer.println("----------------------------------------");
                    count++;
                }
                writer.close();
                return file;
            } catch (Exception e) {
                throw new ReportGenerationFailedException("Inventory report txt generation failed...");
            }
        }
    }

    public void createDir(){
        File dir=new File(ReportConstants.REPORT_PATH);
        dir.mkdirs();
    }
}

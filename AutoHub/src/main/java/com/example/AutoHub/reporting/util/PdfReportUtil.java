//package com.example.AutoHub.reporting.util;
//
//import com.example.AutoHub.exception.NotFoundException;
//import com.example.AutoHub.inventory.entity.Vehicle;
//import com.example.AutoHub.inventory.repository.VehicleRepository;
//import com.example.AutoHub.reporting.constant.ReportConstants;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.print.Doc;
//import java.io.File;
//
//@Component
//public class PdfReportUtil {
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    public File generateSalesReport(String format){
//        return generateFile("sales-report",format);
//    }
//
//    public File generateServiceReport(String format){
//        return generateFile("service-report",format);
//    }
//
//    public File generateInventoryReport(String format){
//        try {
//            File file = new File(ReportConstants.REPORT_PATH + "/inventory-report.pdf");
//            PdfWriter writer = new PdfWriter(file);
//            PdfDocument pdf=new PdfDocument(writer);
//            Document document= new Document(pdf);
//
//            document.add(new Paragraph("AutoHub Inventory Report"));
//
//            for(Vehicle vehicle : vehicleRepository.findAll()){
//                document.add(new Paragraph(
//                    "Brand : "+vehicle.getBrand()+
//                            "Model : "+vehicle.getModel()+
//                            "Manufacture Year : "+vehicle.getManufacturingYear()+
//                            "VIN : "+vehicle.getVin()+
//                            "Vehicle Status : "+vehicle.getVin()+
//                            "Fuel : "+vehicle.getFuelType()+
//                            "Vehicle Type : "+vehicle.getVehicleType()
//                ));
//            }
//            document.close();
//            return file;
//
//        }catch(Exception e){
//            throw new NotFoundException("Inventory Report Generation failed");
//        }
//    }
//
//    private File generateFile(String name,String format){
//        try {
//            File dir = new File(ReportConstants.REPORT_PATH);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            String extension = ReportConstants.PDF.equalsIgnoreCase(format) ? ".pdf" : ".xlsx";
//
//            File file = new File(dir,name + "-" + System.currentTimeMillis() + extension);
//
//            file.createNewFile();
//            return file;
//
//        }catch(Exception e){
//            throw new NotFoundException("Report generation failed");
//        }
//    }
//}

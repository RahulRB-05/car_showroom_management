package com.example.AutoHub.reporting.util;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.reporting.constant.ReportConstants;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;

@Component
public class JasperUtil {

    public File generateSalesReport(String format){
        return generateFile("sales-report",format);
    }

    public File generateServiceReport(String format){
        return generateFile("service-report",format);
    }

    public File generateInventoryReport(String format){
        return generateFile("inventory-report",format);
    }

    private File generateFile(String name,String format){
        try {
            File dir = new File(ReportConstants.REPORT_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String extension = ReportConstants.PDF.equalsIgnoreCase(format) ? ".pdf" : ".xlsx";

            File file = new File(dir + name + "-" + System.currentTimeMillis() + extension);
            return file;

        }catch(Exception e){
            throw new NotFoundException("Report generation failed");
        }
    }
}

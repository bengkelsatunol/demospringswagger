package com.example.demospringswagger;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ReportService {
    
    private static final Logger LOGGER = Logger.getLogger(ReportService.class.getName());

    public byte[] generateReport(Map<String, Object> parameters, List<?> dataSource) throws JRException {
        InputStream reportStream = getClass().getResourceAsStream("/reports/sample_report.jrxml");
        if (reportStream == null) {
            throw new JRException("Report template not found");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        LOGGER.info("Report compiled successfully.");

        if (dataSource == null || dataSource.isEmpty()) {
            throw new IllegalArgumentException("Data source is empty.");
        }

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        LOGGER.info("Report filled successfully.");

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}


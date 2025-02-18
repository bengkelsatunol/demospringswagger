package com.example.demospringswagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringswagger.ReportService;
import com.example.demospringswagger.model.Pegawai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        Map<String, Object> parameters = new HashMap<>();
        List<?> dataSource = List.of(
            new Pegawai(1, "pegawai", "manager"),
            new Pegawai(2, "pegawai", "staff")
            ); // Your data source here

        try {
            byte[] report = reportService.generateReport(parameters, dataSource);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "sample_report.pdf");
            return ResponseEntity.ok().headers(headers).body(report);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

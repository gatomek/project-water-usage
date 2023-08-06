package pl.gatomek.waterusage.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gatomek.waterusage.dto.WaterDayUsage;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.service.WaterUsageDataService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping( "/rest")
public class WaterDayUsageRestController {

    public WaterDayUsageRestController(WaterUsageDataService waterUsageDataService) {
        this.waterUsageDataService = waterUsageDataService;
    }

    final private WaterUsageDataService waterUsageDataService;

    @GetMapping(value = "/water-day-usage", produces = "text/plain")
    public ResponseEntity<Resource> getWaterUsage() {

        WaterDayUsageReport report = waterUsageDataService.getWaterUsageDiffReport();

        String[] csvHeader = report.getHeaders().stream().toArray(String[]::new);

        List<List<String>> csvBody = new ArrayList<>();
        for (WaterDayUsage diff : report.getDiffs()) {
            csvBody.add(Arrays.asList(diff.getDateTime().toString(), Integer.toString(diff.getZW()), Integer.toString(diff.getCW()), Integer.toString(diff.getTW())));
        }

        ByteArrayInputStream byteArrayOutputStream;

        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out), CSVFormat.DEFAULT.withHeader(csvHeader)
                );
        ) {
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            csvPrinter.flush();
            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );
    }
}


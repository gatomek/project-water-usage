package pl.gatomek.waterusage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.service.WaterUsageDataService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WaterUsageApplication.class)
@ActiveProfiles("it")
public class SomeTest0IT {

    @Autowired
    private WaterUsageDataService waterUsageDataService;

    @Test
    public void readingEmptyFolderTest() {
        WaterDayUsageReport report = waterUsageDataService.getWaterUsageDiffReport();
        int size = report.getDiffs().size();

        assertEquals( 0, size);
    }
}

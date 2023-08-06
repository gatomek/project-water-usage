package pl.gatomek.waterusage;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.gatomek.waterusage.dto.WaterDayUsage;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.service.WaterUsageDataService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WaterUsageApplication.class)
@ActiveProfiles("it2")
public class SomeTest2IT {

    @Autowired
    private WaterUsageDataService waterUsageDataService;

    @Test
    public void readingEmptyFolderTest() {
        WaterDayUsageReport report = waterUsageDataService.getWaterUsageDiffReport();
        int size = report.getDiffs().size();

        assertEquals( 1, size);
        WaterDayUsage diff = report.getDiffs().get( 0);
        assertEquals( 5000, diff.getZW());
        assertEquals( 7000, diff.getCW());
        assertEquals( 12000, diff.getTW());
    }
}

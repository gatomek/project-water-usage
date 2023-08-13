package pl.gatomek.waterusage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.service.WaterUsageDataService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("it")
public class FirstTest {

    @Autowired
    private WaterUsageDataService waterUsageDataService;

    @Test
    public void readingEmptyFolderTest() {
        WaterDayUsageReport report = waterUsageDataService.getWaterUsageDiffReport();
        int size = report.getDiffs().size();

        assertEquals( 0, size);
    }
}

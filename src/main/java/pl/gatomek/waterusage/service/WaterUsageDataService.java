package pl.gatomek.waterusage.service;

import org.springframework.stereotype.Component;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.manager.WaterUsageManager;

@Component
public class WaterUsageDataService {

    public WaterUsageDataService( WaterUsageManager waterUsageManager) {
        this.waterUsageManager = waterUsageManager;
    }

    final private WaterUsageManager waterUsageManager;

    public WaterDayUsageReport getWaterUsageDiffReport() {
        return waterUsageManager.makeWaterUsageDiffReport();
    }
}

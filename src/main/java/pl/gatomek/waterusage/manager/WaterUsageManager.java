package pl.gatomek.waterusage.manager;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.gatomek.waterusage.dto.WaterDayCounter;
import pl.gatomek.waterusage.dto.WaterDayUsage;
import pl.gatomek.waterusage.dto.WaterDayUsageReport;
import pl.gatomek.waterusage.tools.WaterDayUsageFileParser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class WaterUsageManager {

    @Value("${data-folder}")
    private String dataFolderPath;

    public WaterDayUsageReport makeWaterUsageDiffReport() {

        System.out.println( dataFolderPath);
        List<WaterDayCounter> usages = new LinkedList<>();

        File dataFolder = new File(dataFolderPath);
        if (dataFolder.exists()) {
            File[] yearFolders = dataFolder.listFiles();
            for (File yearFolder : yearFolders) {
                if (!yearFolder.isDirectory())
                    continue;

                String yearFolderName = yearFolder.getName();
                File[] monthFolders = yearFolder.listFiles();
                for (File monthFolder : monthFolders) {
                    if (!monthFolder.isDirectory())
                        continue;

                    String monthFolderName = monthFolder.getName();
                    File[] dayFiles = monthFolder.listFiles();
                    for (File dayFile : dayFiles) {
                        if (dayFile.isDirectory())
                            continue;

                        String dayFileName = dayFile.getName();
                        String dayFileNameWithoutExt = Files.getNameWithoutExtension(dayFileName);
                        String dateTime = yearFolderName + "-" + monthFolderName + "-" + dayFileNameWithoutExt;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate ld = LocalDate.parse(dateTime, formatter);

                        try {
                            usages.add(WaterDayUsageFileParser.parse(ld, dayFile));
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        }

        usages.sort((a, b) -> {
            return a.getDateTime().compareTo(b.getDateTime());
        });

        List<WaterDayUsage> diffs = new LinkedList<>();

        for( int i = 0; i < usages.size()-1; i ++) {
            WaterDayCounter prevWaterDayCounter = usages.get(i);
            LocalDate ld = prevWaterDayCounter.getDateTime();
            LocalDate keyld = ld.plusDays(1);

            WaterDayCounter nextWaterDayCounter = usages.get( i + 1);
            if( keyld.isEqual( nextWaterDayCounter.getDateTime())) {
                int deltaZw = (int) (1000 * (nextWaterDayCounter.getZw() - prevWaterDayCounter.getZw()));
                int deltaCw = (int) (1000 * (nextWaterDayCounter.getCw() - prevWaterDayCounter.getCw()));

                diffs.add( WaterDayUsage.of( ld, deltaZw, deltaCw));
            }
        }

        WaterDayUsageReport diffReport = new WaterDayUsageReport();
        diffReport.setHeaders( Arrays.asList( "Date","ZW", "CW", "TW"));
        diffReport.setDiffs( diffs);

        return diffReport;
    }
}

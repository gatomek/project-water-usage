package pl.gatomek.waterusage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WaterDayUsageReport {
    private List<WaterDayUsage> diffs;
    private List<String> headers;
}

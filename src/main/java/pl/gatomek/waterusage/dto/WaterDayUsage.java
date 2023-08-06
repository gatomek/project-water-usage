package pl.gatomek.waterusage.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterDayUsage {

    static public WaterDayUsage of(LocalDate dateTime, int ZW, int CW) {
        return new WaterDayUsage( dateTime, ZW, CW);
    }

    public WaterDayUsage(LocalDate dateTime, int ZW, int CW) {
        this.dateTime = dateTime;
        this.ZW = ZW;
        this.CW = CW;
        this.TW = ZW + CW;
    }
    private LocalDate dateTime;
    private int ZW;
    private int CW;
    private int TW;
}

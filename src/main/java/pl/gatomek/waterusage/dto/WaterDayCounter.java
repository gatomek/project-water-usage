package pl.gatomek.waterusage.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterDayCounter {
    public WaterDayCounter(Float zw, Float cw, LocalDate dateTime) {
        this.zw = zw;
        this.cw = cw;
        this.dateTime = dateTime;
    }

    private Float zw;
    private Float cw;
    private LocalDate dateTime;
}

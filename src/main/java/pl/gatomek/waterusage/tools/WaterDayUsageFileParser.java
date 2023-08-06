package pl.gatomek.waterusage.tools;

import pl.gatomek.waterusage.dto.WaterDayCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class WaterDayUsageFileParser {
    public static WaterDayCounter parse(LocalDate ldt, File file ) throws IOException {

        try( BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));)
        {
            Float zw = null;
            Float cw = null;

            String line = reader.readLine();

            while (line != null) {
                if( Objects.isNull( zw))
                    zw = parseFloat( line, "ZW::");

                if( Objects.isNull(cw))
                    cw = parseFloat( line, "CW::");

                if( Objects.nonNull( zw) && Objects.nonNull( cw))
                    break;

                line = reader.readLine();
            }

            if(Objects.nonNull( zw) || Objects.nonNull( cw))
                return new WaterDayCounter( zw, cw, ldt);

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IOException();
    }

    static private Float parseFloat( String line, String startsWith) {
        Float value = null;
        try {
            if( line.startsWith( startsWith))
                value = Float.valueOf( line.substring( 4));
        }
        catch(Exception ex) {
        }
        return value;
    }
}

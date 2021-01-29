package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class ParseDates {

    public LocalDateTime parseString(String string) {
        DateTimeFormatter dateTimeFormatter1 = new DateTimeFormatterBuilder()
                .appendPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy][dd MMM yyyy][dd MM yyyy][ddMMyyyy]")
                .optionalStart().appendPattern(" HHmm").optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        return LocalDateTime.parse(string, dateTimeFormatter1);
    }
}

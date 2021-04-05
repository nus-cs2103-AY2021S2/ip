package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class ParseDates {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy][dd MMM yyyy][dd MM yyyy][ddMMyyyy]")
                .optionalStart().appendPattern(" HHmm").optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

    public boolean isParsable(String dateInput) {
        try {
            LocalDateTime.parse(dateInput, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDateTime parseString(String string) {
        return LocalDateTime.parse(string, DATE_TIME_FORMATTER);
    }
}

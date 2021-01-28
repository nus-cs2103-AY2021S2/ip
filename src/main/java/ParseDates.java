import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class ParseDates {

    public LocalDateTime parseString(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy][dd MM yyyy][ddMMyyyy]");
        DateTimeFormatter dateTimeFormatter1 = new DateTimeFormatterBuilder()
                .appendPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy][dd MM yyyy][ddMMyyyy]")
                .optionalStart().appendPattern(" HHmm").optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        LocalDateTime date = LocalDateTime.parse(string, dateTimeFormatter1);
        return date;
    }
}

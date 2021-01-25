import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;

public class DateTime {
    private final LocalDateTime ldt;
    private final boolean dateOnly;

    private final static DateTimeFormatter PARSE_FORMATTER;
    private final static DateTimeFormatter DATETIME_FORMATTER;
    private final static DateTimeFormatter DATE_FORMATTER;

    static {
        String[] patterns = new String[]{"dd/MM/yy", "dd/MM/yyyy", "dd/MM/yyyy HHmm"};
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);

        PARSE_FORMATTER = builder
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    }

    public DateTime(String str) {
        // Length of dd/MM/yyyy
        dateOnly = str.length() <= 10;
        ldt = LocalDateTime.parse(str, PARSE_FORMATTER);
    }

    @Override
    public String toString() {
        if (dateOnly) {
            return  ldt.format(DATE_FORMATTER);
        }

        return ldt.format(DATETIME_FORMATTER);
    }
}

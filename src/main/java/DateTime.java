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
        // Put the pattern with more info in front as
        // longer datetime strings are unable to be parsed by shorter formatters
        String[] patterns = new String[]{"dd/MM/yyyy HHmm", "dd/MM/yyyy"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);

        PARSE_FORMATTER = builder
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        DATETIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm dd MMM yyyy");
        DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    }

    public DateTime(String str) {
        // Length of dd/MM/yyyy
        dateOnly = str.length() <= 10;
        ldt = LocalDateTime.parse(str, PARSE_FORMATTER);
    }

    private DateTime(LocalDateTime ldt, boolean dateOnly) {
        this.ldt = ldt;
        this.dateOnly = dateOnly;
    }

    public String toISODateTime() {
        return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static DateTime fromISODateTime(String str, boolean dateOnly) {
        return new DateTime(LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME), dateOnly);
    }

    public boolean getDateOnly() {
        return dateOnly;
    }

    @Override
    public String toString() {
        if (dateOnly) {
            return ldt.format(DATE_FORMATTER);
        }

        return ldt.format(DATETIME_FORMATTER);
    }
}

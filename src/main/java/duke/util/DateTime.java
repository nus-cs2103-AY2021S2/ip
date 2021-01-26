package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;

/**
 * Represents a date-time object without a timezone.
 */
public class DateTime {
    private final LocalDateTime ldt;
    private final boolean dateOnly;

    /** Formatter used for parsing date/datetime strings */
    private final static DateTimeFormatter PARSE_FORMATTER;

    /** Output formatter used when instance does not have dateOnly */
    private final static DateTimeFormatter DATETIME_FORMATTER;

    /** Output formatter used when instance has dateOnly */
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

    /**
     * Returns an ISO-8601 datetime string. If the instance was created with a date string (without time),
     * hour, minute and second fields will be defaulted to 0.
     *
     * @return an ISO-8601 datetime string
     */
    public String toISODateTime() {
        return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Recreates a DateTime instance from an ISO-8601 datetime string. The dateOnly parameter will indicate if the time
     * details should be ignored.
     *
     * @param str the ISO-8601 datetime string
     * @param dateOnly the boolean indicating if time details should be ignored
     * @return a DateTime instance created from the given ISO-8601 datetime string
     */
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

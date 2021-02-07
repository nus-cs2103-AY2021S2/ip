package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a date-time object without a timezone.
 */
public class DateTime {
    /** Formatter used for parsing date/datetime strings */
    private static final DateTimeFormatter PARSE_FORMATTER;

    /** Output formatter used when instance does not have dateOnly */
    private static final DateTimeFormatter DATETIME_FORMATTER;

    /** Output formatter used when instance has dateOnly */
    private static final DateTimeFormatter DATE_FORMATTER;

    static {
        // Put the pattern with more info in front as longer datetime strings with additional info
        // such as HHmm are unable to be parsed by shorter formatters.
        String[] patterns = new String[]{"dd/MM/yyyy HHmm", "dd/MM/yyyy"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);

        // Put in default values for hour, minute and second or else the formatter will fail.
        PARSE_FORMATTER = builder
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        DATETIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm dd MMM yyyy");
        DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    }

    private final LocalDateTime ldt;
    private final boolean dateOnly;

    /**
     * Constructor to create a DateTime object from the given string. The string should match one of the
     * formats specified in PARSE_FORMATTER.
     *
     * @param dateTimeStr the dateTimeStr to be parsed by PARSE_FORMATTER
     */
    public DateTime(String dateTimeStr) {
        // Length of dd/MM/yyyy
        dateOnly = dateTimeStr.length() <= 10;
        ldt = LocalDateTime.parse(dateTimeStr, PARSE_FORMATTER);
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
    public String toIsoDateTime() {
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
    public static DateTime fromIsoDateTime(String str, boolean dateOnly) {
        return new DateTime(LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME), dateOnly);
    }

    public boolean getDateOnly() {
        return dateOnly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DateTime dateTime = (DateTime) o;
        return dateOnly == dateTime.dateOnly && ldt.equals(dateTime.ldt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ldt, dateOnly);
    }

    @Override
    public String toString() {
        if (dateOnly) {
            return ldt.format(DATE_FORMATTER);
        }

        return ldt.format(DATETIME_FORMATTER);
    }
}

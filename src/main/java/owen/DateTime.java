package owen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Stores a date and time that can be formatted in
 * DD/MM/YYYY HHMM or Month DD YYYY HH:MM AM/PM.
 */
public class DateTime {
    private static final DateTimeFormatter inputFormatter;
    private static final DateTimeFormatter outputFormatter;
    private final LocalDateTime dateTime;

    static {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder = builder
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral("/")
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("/")
                .appendValue(ChronoField.YEAR)
                .appendLiteral(" ")
                .appendValue(ChronoField.HOUR_OF_DAY)
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2);
        inputFormatter = builder.toFormatter();

        builder = new DateTimeFormatterBuilder();
        builder = builder
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral(" ")
                .appendValue(ChronoField.YEAR)
                .appendLiteral(" ")
                .appendValue(ChronoField.CLOCK_HOUR_OF_AMPM)
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                .appendLiteral(" ")
                .appendText(ChronoField.AMPM_OF_DAY);
        outputFormatter = builder.toFormatter();
    }

    /**
     * Creates new DateTime from LocalDateTime.
     *
     * @param dateTime LocalDateTime object.
     */
    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Parse a date time string in DD/MM/YYYY HHMM format.
     *
     * @param string Date time string in DD/MM/YYYY HHMM format.
     * @return DateTime from parsed string.
     * @throws OwenException DateTime could not be parsed.
     */
    public static DateTime parse(String string) throws OwenException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(string, DateTime.inputFormatter);
            return new DateTime(dateTime);
        } catch (DateTimeParseException exception) {
            throw new OwenException("Date and time must be in DD/MM/YYYY HHMM format...");
        }
    }

    /**
     * Returns if date time is soon within specified days.
     */
    public boolean isSoon(long days) {
        return this.dateTime.minusDays(days).isBefore(LocalDateTime.now());
    }

    /**
     * Returns date time string in DD/MM/YYYY HHMM format.
     *
     * @return Date time string in DD/MM/YYYY HHMM format.
     */
    public String getAsInputFormat() {
        return this.dateTime.format(DateTime.inputFormatter);
    }

    /**
     * Returns date time string in Month DD YYYY HH:MM AM/PM format.
     *
     * @return Date time string in Month DD YYYY HH:MM AM/PM format.
     */
    public String getAsOutputFormat() {
        return this.dateTime.format(DateTime.outputFormatter);
    }

    @Override
    public String toString() {
        return this.getAsOutputFormat();
    }
}

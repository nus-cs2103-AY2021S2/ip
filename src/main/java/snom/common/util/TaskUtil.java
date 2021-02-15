package snom.common.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Container for date time formatter.
 */
public class TaskUtil {
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("E dd MMM yyyy hh:mma")
            .toFormatter();
    public static final DateTimeFormatter DATE_TIME_SAVE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm]")
            .toFormatter();
}

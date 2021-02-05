package popo.utils;

import java.time.format.DateTimeFormatter;

/**
 * Specify the date time formats used as output.
 */
public class OutputDateTimeFormat {
    /**
     * Output date format, e.g. Jan 23 2021
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d y");

    /**
     * Output time format, e.g. 7:33PM
     */
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");
}

package kuga.utils;

import java.time.format.DateTimeFormatter;

/**
 * Specify the date time formats used as input.
 */
public class InputDateTimeFormat {
    /**
     * User input date format, e.g. 23/1/2021
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/y");

    /**
     * User input time format in 24-hour clock, e.g. 1800
     */
    public static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
}

package duke.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Format date and time.
 */
public class DateTime {
    /**
     * Returns String representation of input date.
     *
     * @param date Date to be converted to string.
     * @return String representation of date.
     */
    public static String getDateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns String representation of input time.
     *
     * @param time Time to be converted to string.
     * @return String representation of time.
     */
    public static String getTimeAsString(LocalTime time) {
        return time == null ? "" : time.toString();
    }

    /**
     * Returns String representation of date to be saved in safe file.
     *
     * @param date Date to be saved.
     * @return String representation of date.
     */
    public static String serialiseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a LocalDate from an input string.
     * The input string may have an optional time field.
     *
     * @param input String representation of date and time.
     * @return LocalDate Date represented by the input string.
     */
    public static LocalDate parseDate(String input) throws DateTimeParseException {
        String[] dateAndTime = input.split(" ");
        return LocalDate.parse(dateAndTime[0]);
    }

    /**
     * Returns a LocalTime from an input string.
     *
     * @param input String representation of date and time.
     * @return LocalTime Time represented by the input string or null if no time is found.
     */
    public static LocalTime parseTime(String input) throws DateTimeParseException {
        String[] dateAndTime = input.split(" ");
        boolean isTimeFieldPresent = dateAndTime.length >= 2;
        return isTimeFieldPresent ? LocalTime.parse(dateAndTime[1]) : null;
    }
}

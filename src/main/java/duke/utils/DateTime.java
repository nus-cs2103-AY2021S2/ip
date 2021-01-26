package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Format date and time.
 */
public class DateTime {
    /**
     * Returns Date represented by input String.
     * @param date String representation of a date.
     * @return LocalDate Date represented by the input string.
     * @throws DateTimeParseException If date cannot be parsed.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
    }

    /**
     * Returns String representation of input Date.
     * @param date Date to be converted.
     * @return String String representation of Date.
     */
    public static String getDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns String representation of date to be saved in safe file.
     * @param date Date to be saved.
     * @return String representation of date.
     */
    public static String serialiseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

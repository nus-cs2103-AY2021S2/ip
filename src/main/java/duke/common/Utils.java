package duke.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper methods used to validate and format inputs.
 */
public class Utils {

    /**
     * Returns a formatted date String.
     *
     * @param date date to be formatted
     * @return formatted date String
     */
    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return date.format(formatter);
    }

    /**
     * Returns a formatted date String.
     *
     * @param dateString a date String to be formatted
     * @return formatted date String
     */
    public static String formatDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        LocalDateTime date = LocalDateTime.parse(dateString);
        return date.format(formatter);
    }

    /**
     * Returns true if the String is a valid date.
     *
     * @param dateString a date String to be checked
     */
    public static boolean checkIsValidDate(String dateString) {
        try {
            LocalDateTime.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the String is a number.
     *
     * @param numberString a number String to be checked
     */
    public static boolean checkIsNumeric(String numberString) {
        try {
            Integer.parseInt(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

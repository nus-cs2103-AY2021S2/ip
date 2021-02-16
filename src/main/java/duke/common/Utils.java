package duke.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Helper methods used to validate and format inputs.
 */
public class Utils {
    public static final DateTimeFormatter DATETIME_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd HH:mm]")
            .appendPattern("[yyyy-MM-dd]")
            .appendPattern("[d-M-yyyy HH:mm]")
            .appendPattern("[d-M-yyyy]")
            .appendPattern("[yyyy/MM/dd HH:mm]")
            .appendPattern("[yyyy/MM/dd]")
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d/M/yyyy]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd]")
            .appendPattern("[d-M-yyyy]")
            .appendPattern("[yyyy/MM/dd]")
            .appendPattern("[d/M/yyyy]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();


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
     * @param date date to be formatted
     * @return formatted date String
     */
    public static String formatStorageDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    /**
     * Returns true if the String is an invalid date.
     *
     * @param dateString a date String to be checked
     */
    public static boolean checkIsInvalidDate(String dateString) {
        try {
            LocalDate.parse(dateString, DATE_FORMAT);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    /**
     * Returns true if the String is an invalid datetime.
     *
     * @param dateTimeString a datetime String to be checked
     */
    public static boolean checkIsInvalidDateTime(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, DATETIME_FORMAT);
            return false;
        } catch (DateTimeParseException e) {
            return true;
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

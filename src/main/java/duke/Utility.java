package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility with methods to be used in multiple classes
 */
public class Utility {

    /**
     * Used for DukeException handling, to check if user provides valid numeric selection when necessary
     *
     * @param text String to check for numeric value
     * @return True if valid numeric, false if invalid
     */
    public static boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Used for DukeException handling, to check if user provides valid date
     *
     * @param date Date in string format of yyyy-MM-dd
     * @return True if valid, false if invalid
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }
}

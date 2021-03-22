package seashell.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator {

    /**
     * Checks if a string is a valid date time
     * @param input
     * @return
     */
    public static boolean isValid(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

package duke.datevalidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidatorLocalDate implements DateValidator {
    private DateTimeFormatter dateFormatter;

    /**
     * DateValidatorLocalDate Constructor.
     * @param df {@code dateFormatter} object
     */
    public DateValidatorLocalDate(DateTimeFormatter df) {
        dateFormatter = df;
    }

    @Override
    /**
     * Checks if date given is valid, in the following order:
     * date - time - locale;
     * If all 3 fails, return with invalid, otherwise return results.
     */
    public boolean isValid(String dateStr) {
        LocalDateTime ldt = null;

        try {
            ldt = LocalDateTime.parse(dateStr, this.dateFormatter);
            String result = ldt.format(dateFormatter);
            return result.equals(dateStr);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(dateStr, dateFormatter);
                String result = ld.format(dateFormatter);
                return result.equals(dateStr);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(dateStr, dateFormatter);
                    String result = lt.format(dateFormatter);
                    return result.equals(dateStr);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                    e2.printStackTrace();
                }
            }
        }

        return false;
    }
}


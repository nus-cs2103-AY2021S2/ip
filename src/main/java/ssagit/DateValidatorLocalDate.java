package ssagit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateValidatorLocalDate implements DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidatorLocalDate(DateTimeFormatter df) {
        dateFormatter = df;
    }

    @Override
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


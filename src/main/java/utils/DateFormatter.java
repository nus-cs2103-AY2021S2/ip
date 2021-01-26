package utils;

import exceptions.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static LocalDate encodeDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("An error occurred while encoding date,"
                    + " use the yyyy-mm-dd format.");
        }
    }

    public static String decodeDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public static String decodeDateForStorage(LocalDate date) {
        String dateVal = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateVal.trim();
        dateVal.replace(" ", "-");
        return dateVal;
    }
}
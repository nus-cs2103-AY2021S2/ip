package utils;

import exceptions.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static LocalDate encodeDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("An error occurred while encoding date: " + e.getMessage());
        }
    }

    public static String decodeDate(LocalDate date) throws DukeException {
        try {
            return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeException e) {
            throw new DukeException("An error occurred while decoding date: " + e.getMessage());
        }
    }
}

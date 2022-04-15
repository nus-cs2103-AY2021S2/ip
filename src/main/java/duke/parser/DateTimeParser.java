package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateTimeException;

/**
 * A class represents a DateTimeParser.
 */
public class DateTimeParser implements Parser {
    /**
     * Returns a LocalDateTime from a string containing date and time.
     * @param dateTime The input string containing date and time.
     * @return A LocalDateTime parsed from the input string.
     * @throws InvalidDateTimeException If the input date and time string is not valid.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws InvalidDateTimeException {
        String[] dateAndTime = dateTime.trim().split(" ");

        LocalDateTime parsedDateTime;

        if (dateAndTime.length != 2) {
            throw new InvalidDateTimeException("Input date and time is not valid!");
        }
        String date = dateAndTime[0].trim();
        String time = dateAndTime[1].trim();
        parsedDateTime = LocalDateTime.of(parseDate(date), parseTime(time));
        return parsedDateTime;
    }

    private static LocalDate parseDate(String date) throws InvalidDateTimeException {
        String[] yearMonthDay = date.strip().split("-");
        if (yearMonthDay.length != 3) {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
        String year = yearMonthDay[0].trim();
        String month = yearMonthDay[1].trim();
        String day = yearMonthDay[2].trim();
        if (year.length() == 4 && month.length() == 2 && day.length() == 2) {
            try {
                return LocalDate.parse(date);
            } catch (DateTimeException e) {
                throw new InvalidDateTimeException("Input date is not valid.");
            }
        } else {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
    }

    private static LocalTime parseTime(String time) throws InvalidDateTimeException {
        String hour = time.trim().substring(0, 2);
        String minute = time.trim().substring(2, 4);
        try {
            return LocalTime.parse(hour + ":" + minute);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Input time is not valid.");
        }
    }
}

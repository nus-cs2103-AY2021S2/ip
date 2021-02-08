package popo.parser;

import static popo.utils.Messages.MESSAGE_FOLLOW_USAGE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import popo.commands.InvalidDescriptionException;
import popo.utils.InputDateTimeFormat;

/**
 * Contains utility methods used for parsing dates and time in the Parser class.
 */
public class ParserUtil {
    private static final String MESSAGE_INVALID_DATE_FORMAT = "Unable to parse date.";
    private static final String MESSAGE_INVALID_TIME_FORMAT = "Unable to parse time.";
    private static final String MESSAGE_DATE_FORMAT = "Date format: dd/mm/yyyy";
    private static final String MESSAGE_TIME_FORMAT = "Time format (24-hour clock): HHMM";

    /**
     * Parses the input date string format into a {@code LocalDate} object.
     *
     * @param dateString User input date string.
     * @return {@code LocalDate} object representing the date.
     * @throws InvalidDescriptionException If the format of the date is invalid.
     */
    public static LocalDate parseDate(String dateString) throws InvalidDescriptionException {
        try {
            return LocalDate.parse(dateString, InputDateTimeFormat.INPUT_DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_DATE_FORMAT + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + MESSAGE_DATE_FORMAT);
        }
    }

    /**
     * Parses the input time string format into a {@code LocalTime} object.
     *
     * @param timeString User input time string.
     * @return {@code LocalTime} object representing the time.
     * @throws InvalidDescriptionException If the format of the time is invalid.
     */
    public static LocalTime parseTime(String timeString) throws InvalidDescriptionException {
        try {
            return LocalTime.parse(timeString, InputDateTimeFormat.INPUT_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TIME_FORMAT + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + MESSAGE_TIME_FORMAT);
        }
    }
}

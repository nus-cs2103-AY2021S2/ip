package com.tanboonji.jhin.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import com.tanboonji.jhin.exception.InvalidDateTimeException;
import com.tanboonji.jhin.exception.JhinException;

/**
 * The DateParser class helps to parse string input into date time format.
 */
public class DateParser {

    private static final String DATE_FORMAT = "[dd/MM/yyyy][dd-MM-yyyy][dd.MM.yyyy][ddMMyyyy][dd/MM/yy]"
            + "[dd-MM-yy][dd.MM.yy][ddMMyy]";
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DATE_FORMAT)
            .toFormatter();
    private static final String TIME_FORMAT_24HOUR = "[HHmm][HH:mm][HH]";
    private static final DateTimeFormatter TIME_FORMATTER_24HOUR = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_FORMAT_24HOUR)
            .toFormatter();
    private static final String TIME_FORMAT_12HOUR = "[hmma][h:mma][ha]";
    private static final DateTimeFormatter TIME_FORMATTER_12HOUR = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_FORMAT_12HOUR)
            .toFormatter();
    private static final String OUTPUT_FORMAT = "dd MMM yyyy h:mma";
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
    private static final String INVALID_DATE_MESSAGE_FORMAT = "Sorry, the date '%s' you entered is invalid.\n"
            + "Please enter a valid date in the following format:\n"
            + "ddMMyyyy (01012021)\n"
            + "ddMMyy (010121)\n"
            + "dd/MM/yyyy (01/01/2021)\n"
            + "dd/MM/yy (01/01/21)\n"
            + "dd-MM-yyyy (01-01-2021)\n"
            + "dd-MM-yy (01-01-21)\n"
            + "dd.MM.yyyy (01.01.2021)\n"
            + "dd.MM.yy (01.01.21)";
    private static final String INVALID_TIME_MESSAGE_FORMAT = "Sorry, the time '%s' you entered is invalid.\n"
            + "Please enter a valid time in the following format:\n"
            + "hmma (1100PM)\n"
            + "h:mma (11:00PM)\n"
            + "ha (11PM)\n"
            + "HHmm (2300)\n"
            + "HH:mm (23:00)\n"
            + "HH (23)";

    /**
     * Parses input from String class to LocalDateTime class.
     *
     * @param input String input.
     * @return Date time object.
     */
    public static LocalDateTime parseDateTime(String input) throws JhinException {
        String[] tokenizedInput = input.split(" ", 2);
        LocalDate date;
        LocalTime time;

        if (tokenizedInput[0].isEmpty()) {
            date = LocalDate.now();
            time = LocalTime.MAX;
        } else if (tokenizedInput.length == 1) {
            if (isDate(tokenizedInput[0])) {
                date = parseDate(tokenizedInput[0].trim());
                time = LocalTime.MAX;
            } else if (isTime(tokenizedInput[0])) {
                date = LocalDate.now();
                time = parseTime(tokenizedInput[0].trim());
            } else {
                throw new InvalidDateTimeException(String.format(INVALID_DATE_MESSAGE_FORMAT, tokenizedInput[0]));
            }
        } else if (tokenizedInput.length == 2) {
            date = parseDate(tokenizedInput[0].trim());
            time = parseTime(tokenizedInput[1].trim());
        } else {
            throw new InvalidDateTimeException(String.format(INVALID_DATE_MESSAGE_FORMAT, tokenizedInput[0]));
        }

        return LocalDateTime.of(date, time);
    }

    /**
     * Parses input from String class to LocalDate class.
     *
     * @param input String input.
     * @return Date object.
     * @throws JhinException If any error occurs while parsing date from String class to LocalDate class.
     */
    public static LocalDate parseDate(String input) throws JhinException {
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(String.format(INVALID_DATE_MESSAGE_FORMAT, input));
        }
    }

    /**
     * Parses input from String class to LocalTime class.
     *
     * @param input String input.
     * @return Time object.
     * @throws JhinException If any error occurs while parsing time from String class to LocalTime class.
     */
    public static LocalTime parseTime(String input) throws JhinException {
        try {
            return LocalTime.parse(input, TIME_FORMATTER_24HOUR);
        } catch (DateTimeParseException e) {
            // proceed to parse with 12 hour formatter
        }

        try {
            return LocalTime.parse(input, TIME_FORMATTER_12HOUR);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(String.format(INVALID_TIME_MESSAGE_FORMAT, input));
        }
    }

    private static boolean isDate(String input) {
        try {
            parseDate(input);
            return true;
        } catch (JhinException e) {
            return false;
        }
    }

    private static boolean isTime(String input) {
        try {
            parseTime(input);
            return true;
        } catch (JhinException e) {
            return false;
        }
    }

    /**
     * Parses date time object to string in a specified standard format.
     *
     * @param dateTime Date time object to be formatted.
     * @return String of date time object in specified standard format.
     */
    public static String toString(LocalDateTime dateTime) {
        return OUTPUT_FORMATTER.format(dateTime);
    }
}

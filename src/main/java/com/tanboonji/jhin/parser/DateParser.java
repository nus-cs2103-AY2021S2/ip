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
    private static final String OUTPUT_FORMAT = "dd MMM yyyy HHmm";
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
    private static final String INVALID_DATE_MESSAGE_FORMAT = "Sorry, the date '%s' you entered is invalid."
            + "Please enter a valid date in the following format:\n"
            + "ddMMyyyy\n"
            + "ddMMyy\n"
            + "dd/MM/yyyy\n"
            + "dd/MM/yy\n"
            + "dd.MM.yyyy\n"
            + "dd.MM.yy\n"
            + "dd-MM-yyyy\n"
            + "dd-MM-yy";
    private static final String INVALID_TIME_MESSAGE_FORMAT = "Sorry, the time '%s' you entered is invalid."
            + "Please enter a valid time in the following format:\n"
            + "HHmm\n"
            + "HH:mm\n"
            + "HH";
    private static final String TIME_FORMAT = "[HHmm][HH:mm][HH]";
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_FORMAT)
            .toFormatter();

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

        date = parseDate(tokenizedInput[0].trim());

        if (tokenizedInput.length == 1) {
            time = LocalTime.MAX;
        } else {
            time = parseTime(tokenizedInput[1].trim());
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
            return LocalTime.parse(input, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(String.format(INVALID_TIME_MESSAGE_FORMAT, input));
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

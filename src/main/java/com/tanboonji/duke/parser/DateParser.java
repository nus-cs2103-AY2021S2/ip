package com.tanboonji.duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import com.tanboonji.duke.exception.DukeException;

/**
 * The DateParser class helps to parse string input into date time format.
 */
public class DateParser {

    private static final String DATE_FORMAT = "[dd/MM/yyyy][dd-MM-yyyy][dd.MM.yyyy]";
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DATE_FORMAT)
            .toFormatter();
    private static final String OUTPUT_FORMAT = "dd MMM yyyy HHmm";
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid time format.";
    private static final String TIME_FORMAT = "[HHmm][mmHH]";
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
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        String[] tokenizedInput = input.trim().split(" ", 2);
        LocalDate date;
        LocalTime time;

        try {
            date = parseDate(tokenizedInput[0].trim());
        } catch (DukeException e) {
            throw e;
        }

        if (tokenizedInput.length == 1) {
            time = LocalTime.MAX;
        } else {
            try {
                time = parseTime(tokenizedInput[1].trim());
            } catch (DukeException e) {
                throw e;
            }
        }

        return LocalDateTime.of(date, time);
    }

    /**
     * Parses input from String class to LocalDate class.
     *
     * @param input String input.
     * @return Date object.
     */
    public static LocalDate parseDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Parses input from String class to LocalTime class.
     *
     * @param input String input.
     * @return Time object.
     */
    public static LocalTime parseTime(String input) throws DukeException {
        try {
            return LocalTime.parse(input, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_MESSAGE);
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

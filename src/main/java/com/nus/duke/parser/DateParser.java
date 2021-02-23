package com.nus.duke.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;

import com.nus.duke.common.DukeDateTimeParserException;

/**
 * DateParser class is used to parse a string representation of date/time into
 */
public class DateParser {

    public static final String DATE_PATTERN = "[ddMMyyyy][dd-MM-yyyy][dd/MM/yyyy][EEEE]";
    public static final String TIME_12_HOUR_PATTERN = "[h:mm a][h:mma][hmma][hmm a][ha][h a]";
    public static final String TIME_24_HOUR_PATTERN = "[HHmm][HH:mm][Hmm]";
    public static final String DEFAULT_OUTPUT_PATTERN = "dd MMM yyyy[ hh:mm a]";
    public static final DateTimeFormatter TIME_12_HOUR_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_12_HOUR_PATTERN)
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeFormatter TIME_24_HOUR_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_24_HOUR_PATTERN)
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DATE_PATTERN)
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_12_HOUR_PATTERN)
            .appendPattern(TIME_24_HOUR_PATTERN)
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeFormatter DEFAULT_OUTPUT_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_OUTPUT_PATTERN);
    public static final String TIME_FORMAT_MESSAGE = "Time format:\n"
            + "HHmm (2359)\n"
            + "hh:mm (11:59 PM)\n"
            + "hh a (10 PM)";
    public static final String DATE_FORMAT_MESSAGE = "Date format:\n"
            + "ddMMyyyy (25122021)\n"
            + "dd-MM-yyyy (25-12-2021)\n"
            + "dd/MM/yyyy (25/12/2021)\n"
            + "Sunday";
    public static final String IMPROPER_DATETIME_PROVIDED_MESSAGE =
            "Please provide a proper date/time\n\n"
                    + DATE_FORMAT_MESSAGE + "\n\n" + TIME_FORMAT_MESSAGE;

    /**
     * Parses date and time string into LocalDateTime
     *
     * @param userDateTime user input date and time
     * @return parsed date time object
     * @throws DukeDateTimeParserException on any date or time formatting error
     */
    public static LocalDateTime parseDateTime(String userDateTime)
            throws DukeDateTimeParserException {
        if (userDateTime.isEmpty()) {
            throw new DukeDateTimeParserException(IMPROPER_DATETIME_PROVIDED_MESSAGE);
        }
        String trimmedUserDateTime = userDateTime.trim();

        String[] splitDate = trimmedUserDateTime.split(" ", 2);
        LocalDate dateComp;
        LocalTime timeComp;

        if (hasOnlyDateComponent(trimmedUserDateTime) || hasOnlyTimeComponent(
                trimmedUserDateTime)) {
            try {
                timeComp = parseTime(trimmedUserDateTime);
            } catch (DukeDateTimeParserException ex) {
                timeComp = LocalTime.MAX;
            }
            try {
                dateComp = parseDate(trimmedUserDateTime);
            } catch (DukeDateTimeParserException ex) {
                dateComp = LocalDate.now();
            }
            return LocalDateTime.of(dateComp, timeComp);
        }
        if (splitDate[0].isEmpty() && !splitDate[1].isEmpty()) {
            // Has time but no date component
            dateComp = LocalDate.now();
            timeComp = parseTime(splitDate[1]);
            return LocalDateTime.of(dateComp, timeComp);
        }
        if (!splitDate[0].isEmpty() && splitDate[1].isEmpty()) {
            // Has date but no time component
            dateComp = parseDate(splitDate[0]);
            timeComp = LocalTime.MAX;
            return LocalDateTime.of(dateComp, timeComp);
        }

        // Has date and time component
        dateComp = parseDate(splitDate[0]);
        timeComp = parseTime(splitDate[1]);
        return LocalDateTime.of(dateComp, timeComp);
    }

    private static LocalDate parseDate(String userDate) throws DukeDateTimeParserException {
        String trimmedDate = userDate.trim();
        try {
            TemporalAccessor accessor = DATE_FORMATTER.parse(trimmedDate);
            if (accessor.isSupported(ChronoField.DAY_OF_MONTH)) {
                return LocalDate.parse(trimmedDate, DATE_FORMATTER);
            } else {
                LocalDate now = LocalDate.now();
                DayOfWeek dayOfWeek = DayOfWeek.from(accessor);
                return now.with(TemporalAdjusters.next(dayOfWeek));
            }
        } catch (Exception exception) {
            throw new DukeDateTimeParserException(IMPROPER_DATETIME_PROVIDED_MESSAGE);
        }
    }

    private static LocalTime parseTime(String userTime) throws DukeDateTimeParserException {
        String trimmedTime = userTime.trim();
        try {
            return LocalTime.parse(trimmedTime, TIME_12_HOUR_FORMATTER);
        } catch (DateTimeParseException ex) {
            // Ignore because we want to fallback to 24 hour formatter
        }
        try {
            return LocalTime.parse(trimmedTime, TIME_24_HOUR_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new DukeDateTimeParserException(IMPROPER_DATETIME_PROVIDED_MESSAGE);
        }
    }

    /**
     * Checks if the string contains only a date component.
     *
     * @param userDateTime user input
     * @return true if contains valid date
     */
    public static boolean hasOnlyDateComponent(String userDateTime) {
        try {
            DATE_FORMATTER.parse(userDateTime.trim());
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    /**
     * Checks if the string contains only a time component.
     *
     * @param userDateTime user input
     * @return true if contains valid time
     */
    public static boolean hasOnlyTimeComponent(String userDateTime) {
        try {
            TIME_FORMATTER.parse(userDateTime.trim());
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
}

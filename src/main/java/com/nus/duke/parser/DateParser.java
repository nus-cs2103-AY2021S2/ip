package com.nus.duke.parser;

import com.nus.duke.common.DukeDateParserException;
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

public class DateParser {

    public static final String DEFAULT_DATE_PATTERN = "[dd-MM-yyyy][dd/MM/yyyy][EEEE]";
    public static final String DEFAULT_TIME_PATTERN = "[h:mm a][h:mma][hmma][hmm a][ha][h a][HHmm][HH:mm][Hmm]";
    public static final String DEFAULT_OUTPUT_PATTERN = "dd MMM yyyy[ hh:mm a]";
    public static final DateTimeFormatter DEFAULT_OUTPUT_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_OUTPUT_PATTERN);

    public static final DateTimeFormatter DEFAULT_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DEFAULT_DATE_PATTERN)
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DEFAULT_TIME_PATTERN)
            .toFormatter();

    public static LocalDateTime parseDateTime(String userDate) throws DukeDateParserException {
        LocalDate dateComp = null;
        LocalTime timeComp = null;
        try {
            dateComp = DateParser.parseDate(userDate);
            timeComp = DateParser.parseTime(userDate);
        } catch (DukeDateParserException ignored) {
            // Ignored because we want to fallback to alternative parsing
        }
        if (dateComp == null && timeComp == null) {
            throw new DukeDateParserException("Improper date format");
        } else if (dateComp != null && timeComp == null) {
            return LocalDateTime.of(dateComp, LocalTime.MAX);
        } else if (dateComp == null) {
            return LocalDateTime.of(LocalDate.now(), timeComp);
        }
        return LocalDateTime.of(dateComp, timeComp);
    }

    public static LocalDate parseDate(String userDate) throws DukeDateParserException {
        try {
            String[] splitUserDate = userDate.split(" ", 2);
            TemporalAccessor accessor = DEFAULT_FORMATTER.parse(splitUserDate[0]);
            if (accessor.isSupported(ChronoField.DAY_OF_MONTH)) {
                return LocalDate.parse(splitUserDate[0], DEFAULT_FORMATTER);
            } else {
                LocalDate now = LocalDate.now();
                DayOfWeek dayOfWeek = DayOfWeek.from(accessor);
                return now.with(TemporalAdjusters.next(dayOfWeek));
            }
        } catch (Exception exception) {
            throw new DukeDateParserException("Make sure that date is in proper format");
        }
    }

    public static LocalTime parseTime(String userDate) throws DukeDateParserException {
        LocalDate dateComp = null;
        try {
            // Check if there is a date component
            dateComp = DateParser.parseDate(userDate);
        } catch (DukeDateParserException ignored) {
            // Ignored because we want to fallback to alternative parsing
        }

        if (dateComp == null) {
            return LocalTime.parse(userDate, DEFAULT_TIME_FORMATTER);
        } else {
            String[] splitUserDate = userDate.split(" ", 2);
            if (splitUserDate.length == 1) {
                return null;
            } else {
                try {
                    return LocalTime.parse(splitUserDate[1], DEFAULT_TIME_FORMATTER);
                } catch (DateTimeParseException exception) {
                    throw new DukeDateParserException("Make sure that time is in proper format");
                }
            }
        }
    }
}

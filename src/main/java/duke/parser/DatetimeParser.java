package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.exceptions.DukeExceptionIllegalArgument;

/**
 * Parser class.
 *
 * Handles primarily date parsing.
 */
public class DatetimeParser {

    /**
     * List of supported datetime formats for parsing,
     * partially inspired by { @link https://balusc.omnifaces.org/2007/09/dateutil.html }.
     */
    private static final DateTimeFormatter[] DATE_FORMATS_NO_DATES = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("H:mm") // Same/Next day, 24-hour format
    };
    private static final DateTimeFormatter[] DATE_FORMATS_DATE_NO_TIME = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("d/M/yyyy"), // Full date
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd") // Shortcut
    };
    private static final DateTimeFormatter[] DATE_FORMATS_DATE_NO_TIME_AND_YEAR = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("d/M_yyyy"), // Manually add year, since required to parse datetime
            DateTimeFormatter.ofPattern("d MMM_yyyy"),
            DateTimeFormatter.ofPattern("d MMMM_yyyy"),
            DateTimeFormatter.ofPattern("MMM d_yyyy"),
            DateTimeFormatter.ofPattern("MMMM d_yyyy")
    };
    private static final DateTimeFormatter[] DATE_FORMATS_DATETIME = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("H:mm d/M/yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMM yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMMM yyyy"),
            DateTimeFormatter.ofPattern("H:mm yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy H:mm"),
            DateTimeFormatter.ofPattern("d MMMM yyyy H:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
    };
    private static final DateTimeFormatter[] DATE_FORMATS_DATETIME_NO_YEAR = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("H:mm d/M_yyyy"), // Manually add year, since required to parse datetime
            DateTimeFormatter.ofPattern("H:mm d MMM_yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMMM_yyyy"),
            DateTimeFormatter.ofPattern("H:mm MMM d_yyyy"),
            DateTimeFormatter.ofPattern("H:mm MMMM d_yyyy"),
            DateTimeFormatter.ofPattern("d/M H:mm_yyyy"),
            DateTimeFormatter.ofPattern("d MMM H:mm_yyyy"),
            DateTimeFormatter.ofPattern("d MMMM H:mm_yyyy"),
            DateTimeFormatter.ofPattern("MMM d H:mm_yyyy"),
            DateTimeFormatter.ofPattern("MMMM d H:mm_yyyy")
    };
    private static final DateTimeFormatter ISO_DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private static final DateTimeFormatter READABLE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("E, d MMM");
    private static final DateTimeFormatter READABLE_DATE_YEAR_FORMAT =
            DateTimeFormatter.ofPattern("E, d MMM yyyy");
    private static final DateTimeFormatter READABLE_DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("E h:mm a, d MMM");
    private static final DateTimeFormatter READABLE_DATETIME_YEAR_FORMAT =
            DateTimeFormatter.ofPattern("E h:mm a, d MMM yyyy");

    /**
     * Parses datetime string relative to current time.
     *
     * The datetime string must be in the following specified formats, with text
     * months in title case. In some cases, the time or date may be omitted, upon which
     * the parsing rules are as follows:
     *
     * 1. If only time is supplied, parse as upcoming datetime with the specified time.
     * 2. If no time is supplied, parse as upcoming datetime at midnight.
     * 3. If year is not supplied, parse as upcoming datetime with the specified time/day/month.
     * 4. If date and year supplied, parsed as specified datetime.
     *
     * e.g. if datetime string is "13 Jan" and the current date is "25 Jan 2021",
     * this will be parsed as "13 Jan 2022, 00:00".
     *
     * Available date formats:
     * - 13 Sep
     * - 13 Sep 2020
     * - 13 September
     * - 13 September 2020
     * - 13/9
     * - 13/9/2020
     * - Sep 13
     * - September 13
     * - 2020-09-13
     *
     * Available time formats:
     * - 9:23
     * - 23:09
     *
     * Available datetime formats:
     * - 13 Sep 9:03
     * - 13/9 9:03
     * - 9:03 13 Sep
     * - 9:03 13/9/2020
     *
     * @param input Datetime string.
     * @return LocalDateTime.
     * @throws DukeExceptionIllegalArgument When parsing fails.
     */
    public static LocalDateTime parseDate(String input) throws DukeExceptionIllegalArgument {
        return parseDate(input, LocalDateTime.now());
    }

    /**
     * Parses datetime string relative to supplied datetime.
     *
     * Method created primarily for mocking. See parsing rules for parseDate.
     *
     * @param input Datetime string.
     * @param now LocalDateTime.
     * @return LocalDateTime.
     * @throws DukeExceptionIllegalArgument When parsing fails.
     */
    public static LocalDateTime parseDate(String input, LocalDateTime now) throws DukeExceptionIllegalArgument {
        input = input.strip();
        now = now.withSecond(0).withNano(0);
        String inputAddYear = input + "_" + now.getYear(); // to bypass no year parsing, see constants above.
        Optional<LocalDateTime> result;

        result = DatetimeParser.parseDateWithNoDates(input, now);
        if (result.isPresent()) {
            return result.get();
        }

        result = DatetimeParser.parseDateWithDateAndNoTime(input, now);
        if (result.isPresent()) {
            return result.get();
        }

        result = DatetimeParser.parseDateWithDateAndNoTimeYear(inputAddYear, now);
        if (result.isPresent()) {
            return result.get();
        }

        result = DatetimeParser.parseDateWithDateTime(input, now);
        if (result.isPresent()) {
            return result.get();
        }

        result = DatetimeParser.parseDateWithDateTimeAndNoYear(inputAddYear, now);
        if (result.isPresent()) {
            return result.get();
        }
        // None of the date formats parsed
        throw new DukeExceptionIllegalArgument("Datetime format should adhere to the following format:"
                + "\n- Time delimited by ':', e.g. 9:30"
                + "\n- Date delimited by '/' or ' ', e.g. 9/1, 9 Jan"
                + "\n- Date and time separated by a single space ' '");
    }

    private static Optional<LocalDateTime> parseDateWithNoDates(String input, LocalDateTime now) {
        for (DateTimeFormatter dateFormat : DATE_FORMATS_NO_DATES) {
            LocalDateTime date;
            LocalTime readout;
            try {
                readout = LocalTime.parse(input, dateFormat); // parsing attempt
            } catch (DateTimeParseException e) {
                continue;
            }

            /* Replace current datetime with date values */
            date = now.withHour(readout.getHour()).withMinute(readout.getMinute());
            if (date.isBefore(now)) {
                date = date.plusDays(1); // time is within next 24-hour period
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }

    private static Optional<LocalDateTime> parseDateWithDateAndNoTime(String input, LocalDateTime now) {
        for (DateTimeFormatter dateFormat : DATE_FORMATS_DATE_NO_TIME) {
            LocalDateTime date;
            LocalDate readout;
            try {
                readout = LocalDate.parse(input, dateFormat); // parsing attempt
            } catch (DateTimeParseException e) {
                continue;
            }

            /* Set time to midnight */
            date = readout.atTime(0, 0);
            return Optional.of(date);
        }
        return Optional.empty();
    }

    private static Optional<LocalDateTime> parseDateWithDateAndNoTimeYear(String input, LocalDateTime now) {
        for (DateTimeFormatter dateFormat : DATE_FORMATS_DATE_NO_TIME_AND_YEAR) {
            LocalDateTime date;
            LocalDate readout;
            try {
                readout = LocalDate.parse(input, dateFormat); // parsing attempt
            } catch (DateTimeParseException e) {
                continue;
            }

            /* Set time to midnight */
            date = readout.atTime(0, 0);
            if (date.isBefore(now)) {
                date = date.plusYears(1);
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }

    private static Optional<LocalDateTime> parseDateWithDateTime(String input, LocalDateTime now) {
        for (DateTimeFormatter dateFormat : DATE_FORMATS_DATETIME) {
            LocalDateTime date;
            try {
                date = LocalDateTime.parse(input, dateFormat); // parsing attempt
            } catch (DateTimeParseException e) {
                continue;
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }

    private static Optional<LocalDateTime> parseDateWithDateTimeAndNoYear(String input, LocalDateTime now) {
        for (DateTimeFormatter dateFormat : DATE_FORMATS_DATETIME_NO_YEAR) {
            LocalDateTime date;
            try {
                date = LocalDateTime.parse(input, dateFormat); // parsing attempt
            } catch (DateTimeParseException e) {
                continue;
            }
            if (date.isBefore(now)) {
                date = date.plusYears(1);
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }

    /**
     * Returns formatted string based on supplied datetime.
     *
     * If task falls on current year but has not expired, no year will be inserted.
     * Tasks falling midnight is considered not expired on the same day.
     * If task has time specified other than midnight, the time will be inserted.
     *
     * @param dt LocalDateTime.
     * @return Formatted datetime string.
     */
    public static String formatDate(LocalDateTime dt) {
        LocalDateTime now = LocalDateTime.now();
        if (dt.getHour() == 0 && dt.getMinute() == 0) {
            if (dt.getYear() == now.getYear() && now.isBefore(dt.plusDays(1))) {
                return dt.format(READABLE_DATE_FORMAT);
            } else {
                return dt.format(READABLE_DATE_YEAR_FORMAT);
            }
        } else {
            if (dt.getYear() == now.getYear() && now.isBefore(dt)) {
                return dt.format(READABLE_DATETIME_FORMAT);
            } else {
                return dt.format(READABLE_DATETIME_YEAR_FORMAT);
            }
        }
    }

    /**
     * Returns formatted string as complete datetime.
     *
     * @param dt LocalDateTime.
     * @return Formatted datetime string.
     */
    public static String formatDateFull(LocalDateTime dt) {
        return dt.format(READABLE_DATETIME_YEAR_FORMAT);
    }

    /**
     * Returns formatted string in ISO format, i.e. 'YYYY-MM-DD hh:mm'
     *
     * @param dt LocalDateTime.
     * @return Formatted datetime string.
     */
    public static String formatDateIso(LocalDateTime dt) {
        return dt.format(ISO_DATETIME_FORMAT);
    }

    /**
     * Returns LocalDateTime from ISO format string.
     *
     * @param datetimeString datetime string in ISO format
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateIso(String datetimeString) {
        return LocalDateTime.parse(datetimeString, ISO_DATETIME_FORMAT);
    }
}

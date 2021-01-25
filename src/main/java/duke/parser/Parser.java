package duke.parser;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.exceptions.DukeExceptionIllegalDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    // Some date formats inspired from: https://balusc.omnifaces.org/2007/09/dateutil.html
    private static final DateTimeFormatter[] DATE_FORMATS = new DateTimeFormatter[]{
            // Time
            // "hh a", "hh.mm a", // Same/Next day, 12-hour format, 00 minute
            DateTimeFormatter.ofPattern("H:mm"), // Same/Next day, 24-hour format

            // Date
            DateTimeFormatter.ofPattern("d/M/yyyy"), // Full date
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd"), // Shortcut
            DateTimeFormatter.ofPattern("d/M_yyyy"), // Year required to parse datetime
            DateTimeFormatter.ofPattern("d MMM_yyyy"),
            DateTimeFormatter.ofPattern("d MMMM_yyyy"),
            DateTimeFormatter.ofPattern("MMM d_yyyy"),
            DateTimeFormatter.ofPattern("MMMM d_yyyy"),

            // Datetime
            DateTimeFormatter.ofPattern("H:mm d/M/yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMM yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMMM yyyy"),
            DateTimeFormatter.ofPattern("H:mm yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy H:mm"),
            DateTimeFormatter.ofPattern("d MMMM yyyy H:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"),
            DateTimeFormatter.ofPattern("H:mm d/M_yyyy"), // Year required to parse datetime
            DateTimeFormatter.ofPattern("H:mm d MMM_yyyy"),
            DateTimeFormatter.ofPattern("H:mm d MMMM_yyyy"),
            DateTimeFormatter.ofPattern("H:mm MMM d_yyyy"),
            DateTimeFormatter.ofPattern("H:mm MMMM d_yyyy"),
            DateTimeFormatter.ofPattern("d/M H:mm_yyyy"),
            DateTimeFormatter.ofPattern("d MMM H:mm_yyyy"),
            DateTimeFormatter.ofPattern("d MMMM H:mm_yyyy"),
            DateTimeFormatter.ofPattern("MMM d H:mm_yyyy"),
            DateTimeFormatter.ofPattern("MMMM d H:mm_yyyy"),
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

    public static LocalDateTime parseDate(String input) throws DukeExceptionIllegalArgument {
        input = input.strip();
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        String inputAddYear = input+"_"+now.getYear();

        for (int i = 0; i < DATE_FORMATS.length; i++) {
            LocalDateTime date;
            DateTimeFormatter fmt = DATE_FORMATS[i];

            // No date supplied
            if (i == 0) {
                LocalTime readout;
                try {
                    readout = LocalTime.parse(input, fmt);
                } catch (DateTimeParseException e) {
                    continue;
                }
                date = now.withHour(readout.getHour()).withMinute(readout.getMinute());
                if (date.isBefore(now)) {
                    date = date.plusDays(1);
                }
            }

            // No time supplied, assume midnight
            else if (i <= 5) {
                LocalDate readout;
                try {
                    readout = LocalDate.parse(input, fmt);
                } catch (DateTimeParseException e) {
                    continue;
                }
                date = readout.atTime(0, 0);
            }

            // No time and year supplied, manually added
            else if (i <= 10) {
                LocalDate readout;
                try {
                    readout = LocalDate.parse(inputAddYear, fmt);
                } catch (DateTimeParseException e) {
                    continue;
                }
                date = readout.atTime(0, 0);
                if (date.isBefore(now)) {
                    date = date.plusYears(1);
                }
            }

            // All parameters supplied
            else if (i <= 18) {
                try {
                    date = LocalDateTime.parse(input, fmt);
                } catch (DateTimeParseException e) {
                    continue;
                }
            }

            // No year supplied
            else {
                try {
                    date = LocalDateTime.parse(inputAddYear, fmt);
                } catch (DateTimeParseException e) {
                    continue;
                }
                if (date.isBefore(now)) {
                    date = date.plusYears(1);
                }
            }

            // Otherwise fully-supplied
            return date;
        }

        // None of the date formats parsed
        throw new DukeExceptionIllegalArgument("Datetime format should adhere to the following format:"
                + "\n- Time delimited by ':', e.g. 9:30"
                + "\n- Date delimited by '/' or ' ', e.g. 9/1, 9 Jan"
                + "\n- Date and time separated by a single space ' '");
    }

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

    public static String formatDateFull(LocalDateTime dt) {
        return dt.format(READABLE_DATETIME_YEAR_FORMAT);
    }

    public static String formatDateISO(LocalDateTime dt) {
        return dt.format(ISO_DATETIME_FORMAT);
    }
}

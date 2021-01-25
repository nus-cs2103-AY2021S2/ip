package duke.parser;

import duke.exceptions.DukeExceptionIllegalArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter ISO_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter READABLE_DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");

    public static LocalDateTime parseDate(String input) throws DukeExceptionIllegalArgument {
        try {
            return LocalDateTime.parse(input, ISO_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionIllegalArgument("Date must be a valid following format.");
        }
    }

    public static String formatDate(LocalDateTime dt) {
        return dt.format(READABLE_DATE_FORMAT);
    }

    public static String formatDateISO(LocalDateTime dt) {
        return dt.format(ISO_DATE_FORMAT);
    }
}

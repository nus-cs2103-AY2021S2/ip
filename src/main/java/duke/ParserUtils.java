package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class ParserUtils {
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().
            appendPattern("[d/M/yyyy HHmm]").
            appendPattern("[d/M/yyyy]").
            appendPattern("[yyyy-M-d]").
            appendPattern("[yyyy-M-d HH:mm]").
            appendPattern("[MMM d yyyy]").
            parseDefaulting(ChronoField.HOUR_OF_DAY, 0).
            parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).
            toFormatter(Locale.ENGLISH);

    public static LocalDateTime parseDateTime(String datetime, String errorMessage) throws DukeException {
        try {
            return LocalDateTime.parse(datetime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(errorMessage);
        }
    }

    public static int parseInt(String integer, String errorMessage) throws DukeException {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            throw new DukeException(errorMessage);
        }
    }

    public static String[] getCommandArgs(String line, String errorMessage) throws DukeException {
        String[] cmdArgs = line.split(" ", 2);
        if (cmdArgs.length < 2) {
            throw new DukeException(errorMessage);
        }
        if (cmdArgs[1].isEmpty()) {
            throw new DukeException(errorMessage);
        }
        return cmdArgs;
    }
}

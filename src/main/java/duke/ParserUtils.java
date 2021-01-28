package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Utility methods for Parser class.
 */
public class ParserUtils {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().
            appendPattern("[d/M/yyyy HHmm]").
            appendPattern("[d/M/yyyy]").
            appendPattern("[yyyy-M-d]").
            appendPattern("[yyyy-M-d HH:mm]").
            appendPattern("[MMM d yyyy]").
            parseDefaulting(ChronoField.HOUR_OF_DAY, 0).
            parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).
            toFormatter(Locale.ENGLISH);

    /**
     * Parses user-given datetime strings into DateTime objects.
     * Valid datetime formats are specified in FORMATTER.
     * @param datetime datetime string
     * @param errorMessage error message to display if string could not be parsed
     * @return datetime object
     * @throws DukeException
     */
    public static LocalDateTime parseDateTime(String dateTime, String errorMessage) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Parses an integer string into an integer.
     * Similar to Integer.parseInt(String s).
     * @param integer integer string
     * @param errorMessage error message to display if string could not be parsed
     * @return integer value of string
     * @throws DukeException
     */
    public static int parseInt(String integer, String errorMessage) throws DukeException {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Gets arguments for a command.
     * @param line user command
     * @param errorMessage error message to display if arguments cannot be found
     * @return array containing command and its argument
     * @throws DukeException
     */
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

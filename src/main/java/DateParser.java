import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {

    private static final String DATE_FORMAT = "[dd/MM/yyyy][dd-MM-yyyy][dd.MM.yyyy]";
    private static final String TIME_FORMAT = "[HHmm][mmHH]";
    private static final String OUTPUT_FORMAT = "dd MMM yyyy HHmm";
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(DATE_FORMAT)
            .toFormatter();
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(TIME_FORMAT)
            .toFormatter();
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);

    /**
     * Parses input from String class to LocalDateTime class.
     *
     * @param input User string input
     * @return LocalDateTime object.
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

    public static LocalDate parseDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ Sorry, please enter a valid date format.");
        }
    }

    public static LocalTime parseTime(String input) throws DukeException {
        try {
            return LocalTime.parse(input, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ Sorry, please enter a valid time format.");
        }
    }

    /**
     * Returns datetime in a standard format.
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return String of datetime in standard format.
     */
    public static String toString(LocalDateTime dateTime) {
        return OUTPUT_FORMATTER.format(dateTime);
    }
}

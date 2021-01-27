import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

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
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid time format.";

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
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    public static LocalTime parseTime(String input) throws DukeException {
        try {
            return LocalTime.parse(input, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_MESSAGE);
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the dates/times input.
 */
public class DateTimeHandler {

    protected static final String DATE_TIME_FORMAT = "MMM dd yyyy h:mm a";
    protected static final String INPUT_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Checks if the input is in the correct format of "yyyy-MM-dd HHmm".
     *
     * @param details Input to be checked.
     * @return A LocalDateTime object created from the input.
     * @throws DukeException On invalid input. Input format is wrong.
     */
    public static LocalDateTime validateDateTime(String details) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
            LocalDateTime verifiedDate = LocalDateTime.parse(details, formatter);
            return verifiedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException(":( Date format is invalid! Please enter in yyyy-mm-dd HHmm format!");
        }
    }

    /**
     * Creates a LocalDateTime object from a string.
     *
     * @param date String that is used to create a LocalDateTime object.
     * @return A LocalDateTime object created.
     */
    public static LocalDateTime parseStringIntoLocalDateTime(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime createDate = LocalDateTime.parse(date, format);
        return createDate;
    }

    /**
     * Parses LocalDateTime object into a string for tasks, according to given format.
     *
     * @param details LocalDateTime object.
     * @return A String representing the date and time in the new format.
     */
    public static String parseLocalDateTimeIntoString(LocalDateTime details) {
        return details.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

}

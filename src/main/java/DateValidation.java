import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the validation of dates for Deadlines.
 */
public class DateValidation {

    static final String DATE_FORMAT = "MMM dd yyyy h:mm a";
    static final String INPUT_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Checks if the input is in the correct format of "yyyy-MM-dd HHmm".
     *
     * @param date Input to be checked.
     * @return A LocalDateTime object created from the input.
     * @throws DukeException On invalid input. Input format is wrong.
     */
    public static LocalDateTime handleDate(String date) throws DukeException {
        try {
            System.out.println(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
            LocalDateTime verifiedDate = LocalDateTime.parse(date, formatter);
            System.out.println(verifiedDate);
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
    public LocalDateTime convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime createDate = LocalDateTime.parse(date, format);
        return createDate;

    }

}

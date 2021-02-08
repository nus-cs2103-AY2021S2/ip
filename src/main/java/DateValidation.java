import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the validation of dates for Deadlines.
 */
public class DateValidation {

    static final String DATE_FORMAT = "MMM dd yyyy";

    /**
     * Checks if the input is in the correct format of "yyyy-mm-dd".
     *
     * @param date Input to be checked.
     * @return A LocalDate object created from the input.
     * @throws DukeException On invalid input. Input format is wrong.
     */
    public static LocalDate handleDate(String date) throws DukeException {
        try {
            LocalDate verifiedDate = LocalDate.parse(date);
            return verifiedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException(":( Date format is invalid! Please enter in yyyy-mm-dd format!");
        }
    }

    /**
     * Creates a LocalDate object from a string.
     *
     * @param date String that is used to create a LocalDate object.
     * @return A LocalDate object created.
     */
    public LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate createDate = LocalDate.parse(date, format);
        return createDate;

    }

}

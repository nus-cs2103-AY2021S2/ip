import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidation {

    public static LocalDate handleDate(String date) throws DukeException {
        LocalDate verifiedDate = null;
        try {
            verifiedDate = LocalDate.parse(date);
            return verifiedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException(":( Date format is invalid! Please enter in yyyy-mm-dd format!");
        }
    }

    public LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate createDate = LocalDate.parse(date, format);
        return createDate;

    }

}

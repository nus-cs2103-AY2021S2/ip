import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidation {

    public LocalDate handleDate(String date) throws DukeException {
        LocalDate verifiedDate = null;
        boolean isCorrect = false;
        try {
            verifiedDate = LocalDate.parse(date);
            System.out.println(verifiedDate);
            isCorrect = true;
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
        if (isCorrect == false) {
            throw new DukeException(":( Date format is invalid! Please enter in yyyy-mm-dd format!");
        } else {
            return verifiedDate;
        }
    }

}

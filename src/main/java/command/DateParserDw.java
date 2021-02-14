package command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import classes.DuckieException;

/**
 * DateParserDw class to handle conversion of dates for DoWithin commands.
 */
public class DateParserDw {
    /**
     * Method to convert String user input to a Date object
     * to be stored in the Task object.
     * @param userInput the user date input in String format.
     * @return the Date object version of userInput.
     * @throws DuckieException if date is entered in incorrect format.
     */
    public static Date parse(String userInput) throws DuckieException {
        String inputDate = userInput.trim();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DuckieException("please enter date in the format dd-mm-yyyy");
        }
        return date;
    }
}

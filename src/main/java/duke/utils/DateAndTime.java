package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.ErrorBox;
import duke.ui.Ui;



/**
 * This class process the date key in by user and convert it to the right format to be use.
 */
public class DateAndTime {

    /**
     * This method convert a yyyy-mm-dd format of date to MMM d yyyy format.
     *
     * @param date the String representation of date.
     * @return MMM d yyyy format of date and return error message if the given date is in wrong format.
     */
    public static String converter(String date) {
        if (isDashFormat(date)) {
            try {
                LocalDate d1 = LocalDate.parse(date.trim());
                return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                ErrorBox.display(e.getMessage());
                return Ui.WRONG_DATE_FORMAT;
            }
        } else {
            ErrorBox.display(Ui.WRONG_DATE_FORMAT);
            return Ui.WRONG_DATE_FORMAT;
        }
    }


    private static boolean isDashFormat(String date) {
        String[] dashFormat = date.toLowerCase().split("-", 3);
        for (String s : dashFormat) {
            if (!isNumeric(s.trim())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            ErrorBox.display(nfe.getMessage());
            return false;
        }
        return true;
    }

}

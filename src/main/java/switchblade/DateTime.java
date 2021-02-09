package switchblade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * DateTime class to remove storing of user specified dates as a String
 *
 * @author leeyueyang
 */
public class DateTime {
    LocalDate localDate;
    LocalTime localTime;
    LocalDateTime localDateTime;

    /**
     * Returns DateTime object with null datetime when user does not specify time
     *
     * @param date User specified date
     */
    DateTime(String date) {
        this.localDate = LocalDate.parse(date);
        this.localTime = null;
        this.localDateTime = null;
    }

    /**
     * Returns DateTime object with specified datetime when user specifies time
     *
     * @param date User specified date
     * @param time User specified time
     */
    DateTime(String date, String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.localDate = LocalDate.parse(date, dateFormatter);
        this.localTime = LocalTime.parse(time, timeFormatter);
        this.localDateTime = localDate.atTime(localTime);
    }

    /**
     * Used when user needs an output to the switchblade.Ui
     *
     * @return Pretty prints the date and if applicable, time stored in this object
     */
    public String getDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
        } else {
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate);
        }
    }

    /**
     * Used when system needs to store this datetime in file
     *
     * @return String of date and if applicable, time in system interpretable format for future use
     */
    public String savingDatetime() {
        if (localDateTime != null) {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd, HHmm").format(localDateTime);
        } else {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        }
    }

    @Override
    public String toString() {
        return getDatetime();
    }
}
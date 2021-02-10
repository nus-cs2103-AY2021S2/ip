import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {


    public static LocalDate processDate(String date) {
        if (date.contains("/")) {
            String[] dateAndTime = date.split("\\s+");
            return LocalDate.parse(dateAndTime[0], DateTimeFormatter.ofPattern("dd/MM" +
                    "/yyyy"));
        } else {
            return LocalDate.parse(date);
        }
    }

    public static String convertTo12Hr(String time) {
        if (!time.contains("PM".toLowerCase()) || !time.contains("AM".toLowerCase())) {
            int timeDecimal = Integer.parseInt(time);
            int hour = timeDecimal / 100;
            int minutes = timeDecimal % 100;
            if (timeDecimal < 12) {
                return String.format("%d:%d AM", hour, minutes);
            } else if (timeDecimal == 12) {
                return String.format("%d:%d PM", 12, minutes);
            } else {
                return String.format("%d:%d AM", hour - 12, minutes);
            }
        } else {
            return time;
        }
    }


}

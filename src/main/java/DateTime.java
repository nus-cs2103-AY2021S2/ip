import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTime {
    LocalDate localDate;
    LocalTime localTime;
    LocalDateTime localDateTime;

    DateTime(String date) {
        this.localDate = LocalDate.parse(date);
        this.localTime = null;
        this.localDateTime = null;
    }


    DateTime(String date, String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmm");

        this.localDate = LocalDate.parse(date);
        this.localTime = LocalTime.parse(time, dateTimeFormatter);
        this.localDateTime = localDate.atTime(localTime);
    }

    public String getDatetime() {
        if (localDateTime != null)
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
        else
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate);
    }

    @Override
    public String toString() {
        return getDatetime();
    }
}
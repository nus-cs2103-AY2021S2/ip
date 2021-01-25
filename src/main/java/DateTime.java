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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.localDate = LocalDate.parse(date, dateFormatter);
        this.localTime = LocalTime.parse(time, timeFormatter);
        this.localDateTime = localDate.atTime(localTime);
    }

    public String getDatetime() {
        if (localDateTime != null)
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
        else
            return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate);
    }

    public String savingDatetime() {
        if (localDateTime != null)
            return DateTimeFormatter.ofPattern("yyyy-MM-dd, HHmm").format(localDateTime);
        else
            return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
    }

    @Override
    public String toString() {
        return getDatetime();
    }
}
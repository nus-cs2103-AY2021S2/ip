import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Deadline extends Task {

    protected String by;
    protected LocalDate dateTime;
    protected LocalDateTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] dateArr = by.split(" ");
        String[] date = dateArr[0].split("-");
        LocalDate dateTime = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]));
        if(dateArr.length > 1)  {
            String t = dateArr[1];
            LocalTime hm = LocalTime.of(Integer.parseInt(t.substring(0,2)), Integer.parseInt(t.substring(2)));
            time = LocalDateTime.of(dateTime, hm);
        }
        this.dateTime = dateTime;
        this.time = time;
    }

    public String getBy() {
        return this.by;
    }

        @Override
        public String toString () {
            String period = time.getHour() >= 12 ? "PM" : "AM";
            String hour = time.getHour() > 12 ? Integer.toString(time.getHour() - 12)
                    : Integer.toString(time.getHour());
            if(time != null) {
                if(time.getMinute() > 0) {
                    return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                            + " "  + hour + "." + time.getMinute() + period +")";
                } else {
                    return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                            + " "  + hour + period +")";
                }
            } else {
                return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
            }
        }
}


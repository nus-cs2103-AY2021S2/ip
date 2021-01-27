import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;
    private String type = "D";

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        setTime(by);
    }

    public String getType(){
        return this.type;
    }

    public String getTime(){
        String time = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
        return time;
    }

    private void setTime(String time) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
        this.by = dateTime;
    }

    @Override
    public String toString() {
        String time = this.getTime();
        return type + separator + super.toString() + separator + time;
    }
}

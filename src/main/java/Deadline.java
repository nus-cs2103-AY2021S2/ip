import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;
    private final String type = "D";

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
//        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
            this.by = dateTime;

//        } catch (Exception e){
//            throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-mm-dd hh:mm");
//        }
    }

    @Override
    public String toString() {
        String time = this.getTime();
//       this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
//        return "[D]" + super.toString() + " (by: " + time + ")";
        return type + separator + super.toString() + separator + time;
    }
}

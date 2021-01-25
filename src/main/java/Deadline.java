import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;
    private static String type = "D";

    public Deadline(String description, String by) throws DukeException {
        super(description);
        setTime(by);
    }

    private void setTime(String time) throws DukeException{
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
            this.by = dateTime;
        } catch (Exception e){
            throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-mm-dd hh:mm");
        }
    }

    @Override
    public String toString() {
        String time = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}

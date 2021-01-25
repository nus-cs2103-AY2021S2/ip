import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;
    private static String type = "E";

    public Event(String description, String at) throws DukeException{
        super(description);
        setTime(at);
    }

    private void setTime(String time) throws DukeException{
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
            this.at = dateTime;
        } catch (Exception e){
            throw new DukeException("OOPS!! Please follow the correct data/time format: yyyy-mm-dd hh:mm");
        }
    }

//    public String archiveEvent() {
//        String s = type + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
//        return s;
//    }

    @Override
    public String toString() {
        String time = this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}

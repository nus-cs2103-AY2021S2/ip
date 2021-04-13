import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Event extends Task {

    private LocalDate time;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-dd");

    Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time, format);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at:" + this.time.format(DateTimeFormatter.ofPattern("MMM dd YYYY")) + ")";
    }
}

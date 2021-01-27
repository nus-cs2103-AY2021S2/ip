import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate duration;

    public Events(String description, String duration) {
        super(description);
        this.duration = LocalDate.parse(duration);
    }
    @Override
    LocalDate getTime(){
        return this.duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
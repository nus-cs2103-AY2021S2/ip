import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private final LocalDate duration;

    public Events(String name, LocalDate duration) {
        super(name);
        this.duration = duration;
    }

    public Events(String name, LocalDate duration, boolean isDone) {
        super(name, isDone);
        this.duration = duration;
    }

    /**
     * Returns the duration of the event as a LocalDate object
     * @return LocalDate Object
     */
    public LocalDate getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}

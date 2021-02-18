package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private final LocalDate startDate;

    public Events(String name, LocalDate duration) {
        super(name);
        this.startDate = duration;
    }

    public Events(String name, LocalDate duration, boolean isDone) {
        super(name, isDone);
        this.startDate = duration;
    }

    /**
     * Returns the duration of the event as a LocalDate object
     * @return LocalDate Object
     */
    public LocalDate getDuration() {
        return startDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}

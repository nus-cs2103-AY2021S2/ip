package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventTask extends Task {
    protected LocalDate on;
    protected LocalTime from;
    protected LocalTime to;

    public EventTask(String description, LocalDate on, LocalTime from, LocalTime to) {
        super(description);
        this.on = on;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ", " + from + " - " + to + ")";
    }
}

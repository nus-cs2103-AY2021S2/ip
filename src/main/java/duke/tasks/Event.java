package duke.tasks;

import duke.util.DateFormatter;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = "Event";
    }

    public Event(String description, Boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
        this.taskType = "Event";
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateFormatter.decodeDate(at) + ")";
    }
}

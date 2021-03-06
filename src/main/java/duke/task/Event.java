package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks that happen in a specific time for a duration (e.g. a meeting on 2/10/2019 2-4pm)
 */
public class Event extends Task {
    private LocalDate date;

    public Event(String title, boolean isDone, LocalDate date) {
        super(title, isDone);
        this.date = date;
    }

    public Event(String title, LocalDate time) {
        this(title, false, time);
    }

    /**
     * @return a string describing the event task
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks with a deadline (need to be done before a date/time)
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    /**
     * @param title the title of the deadline task
     * @param deadline the deadline of the deadline task
     */
    public Deadline(String title, LocalDate deadline) {
        this(title, false, deadline);
    }

    /**
     * @return a string describing the deadline task
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
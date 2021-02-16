package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate deadLine;

    /**
     * This constructor create a Task which is of type Deadlines
     * @param isDone whether the task is already completed
     * @param eventName description of the event
     * @param deadLine the deadline for the task
     * @return return the task object
     */
    public Deadlines(boolean isDone, String eventName, String deadLine) {
        super(isDone, eventName, "D");
        this.deadLine = LocalDate.parse(deadLine);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " +
                deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

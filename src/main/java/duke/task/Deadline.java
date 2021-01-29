package duke.task;

import java.time.LocalDate;

import duke.utils.DateTime;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    /**
     * Date the task is to be completed by.
     */
    protected LocalDate by;

    /**
     * Creates new instance of deadline.
     *
     * @param description Description of deadline.
     * @param by          Date the task is to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialise() {
        String type = "DEADLINE";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|')
            .append(description).append('|').append(DateTime.serialiseDate(by));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.getDate(by) + ")";
    }
}

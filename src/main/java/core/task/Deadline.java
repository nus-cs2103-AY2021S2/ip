package core.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline type Task.
 */
public class Deadline extends Task {
    private LocalDate byTime;

    /**
     * Creates a new Deadline task with a description. Must contain '/by' after which should be the event time.
     * @param desc - description
     */
    public Deadline(String desc) {
        super(desc);
        assert(desc.contains("/by"));
        var parts = desc.split("/by");
        this.taskDescription = parts[0].trim();
        this.byTime = LocalDate.parse(parts[1].trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byTime.format(DateTimeFormatter.ISO_LOCAL_DATE) + ")";
    }
}

package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task representing a deadline task consisting
 * of additional member by to represent the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Default constructor of deadline which calls the
     * default super constructor:
     * state defaults to UNDONE.
     * createdDateTime defaults to current datetime.
     * @param description   the task description.
     * @param by            the task deadline.
     * @throws Task.EmptyDescriptionException
     */
    public Deadline (String description, LocalDateTime by)
            throws Task.EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    public Deadline (String description, boolean isDone, LocalDateTime createdDateTime,
            LocalDateTime by) throws Task.EmptyDescriptionException {
        super(description, isDone, createdDateTime);
        this.by = by;
    }

    @Override
    public String getTaskInformation(DateTimeFormatter outputFormat) {
        return "[D]" + super.getTaskInformation(outputFormat) + " [ by: "
                + this.by.format(outputFormat) + " ]";
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "D" + delimiter + super.toCommand(delimiter, parseFormat) + delimiter
                + this.by.format(parseFormat);
    }
}

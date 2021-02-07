package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task representing a todo task.
 */
public class ToDo extends Task {
    /**
     * Default constructor of ToDo which calls the
     * default super constructor:
     * state defaults to UNDONE.
     * createdDateTime defaults to current datetime.
     * @param description   the task description.
     * @throws Task.EmptyDescriptionException
     */
    public ToDo (String description) throws Task.EmptyDescriptionException {
        super(description);
    }

    public ToDo (String description, boolean isDone, LocalDateTime createdDateTime)
            throws Task.EmptyDescriptionException {
        super(description, isDone, createdDateTime);
    }

    @Override
    public String getTaskInformation(DateTimeFormatter outputFormat) {
        return "[T]" + super.getTaskInformation(outputFormat);
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "T" + delimiter + super.toCommand(delimiter, parseFormat);
    }
}

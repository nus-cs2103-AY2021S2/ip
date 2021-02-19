import java.time.LocalDateTime;

/**
 * Represents the ToDo Task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    /**
     * Initializes a ToDo Task with the specified description, timeCreated, and dateTime.
     *
     * @param description Description of the ToDo Task.
     * @param timeCreated DateTime when this ToDo object was created.
     */
    public ToDo(String description, LocalDateTime timeCreated) {
        super(description, timeCreated, "T");
    }
}


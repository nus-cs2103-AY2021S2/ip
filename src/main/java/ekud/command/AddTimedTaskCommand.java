package ekud.command;

import java.time.LocalDateTime;

/**
 * Command that creates a task with date and time information.
 */
public abstract class AddTimedTaskCommand extends AddCommand {
    protected LocalDateTime dateTime;

    /**
     * Construct a task with date and time information.
     *
     * @param description The description of the task with date and time.
     * @param dateTime    The date and time information of the task.
     */
    public AddTimedTaskCommand(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
}

package duke.command;

/**
 * Creates a Todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a {@code TodoCommand} object with a task description component only.
     * @param description task description
     */
    public TodoCommand(String description) {
        super("todo");
        this.description = description;
    }

    /**
     * Returns description of Todo task.
     * @return description of Todo task
     */
    public String getDescription() {
        return description;
    }
}

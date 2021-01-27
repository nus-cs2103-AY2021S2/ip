package duke.command;

/**
 * Creates a Todo task.
 */
public class TodoCommand extends Command{
    String description;
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

    @Override
    public void run() {

    }
}

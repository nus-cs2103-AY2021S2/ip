package duke.command;

/** Stores information of a task that is to be added. */
public abstract class AddCommand extends Command {
    protected String description;

    /** Asserts that all commands for adding tasks must contain description. */
    public AddCommand(String description) {
        super("Got it. I've added this task:\n");
        this.description = description;
    }
}

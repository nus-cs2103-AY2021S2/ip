/**
 * Represents a valid Command for the Duke object.
 */

public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage);
    public abstract String toDukeOutput();
}

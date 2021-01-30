/**
 * Represents a valid Command for the Duke object.
 */

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract String toDukeOutput();
}

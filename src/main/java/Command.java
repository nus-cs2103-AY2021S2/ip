/**
 * Represents a valid Command for the Henchman object.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage);
    public abstract String toHenchmanOutput();
}

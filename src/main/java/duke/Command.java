package duke;

/**
 * Command class for Duke to understand different commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tl, Ui ui, Storage storage) throws DukeException;
}


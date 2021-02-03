package duke;

public abstract class Command {
    public abstract String execute(TaskList tl, Ui ui, Storage storage) throws DukeException;
}

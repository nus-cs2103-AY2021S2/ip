package duke;

public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage storage) throws DukeException;
}
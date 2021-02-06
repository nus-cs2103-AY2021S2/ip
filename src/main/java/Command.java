abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.EmptyTaskListError, DukeException.TaskOutOfBoundsError,
            DukeException.NoMatchFound, DukeException.FileLoadError;
    public abstract Boolean isExit();
}

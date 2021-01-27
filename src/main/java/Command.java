public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException;
    public abstract boolean isExit();
}
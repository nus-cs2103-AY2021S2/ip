public abstract class Command {
    protected String commandType;
    protected String commandDetails;
    protected String dateTime;
    protected String outputMessage;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean continueInput();
}

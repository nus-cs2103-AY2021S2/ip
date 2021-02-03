public abstract class Command {
    public abstract String execute(Storage storage, TaskList taskList, Ui ui, String command);
}

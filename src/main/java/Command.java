abstract class Command {
    protected static Ui ui;
    protected static Storage storage;
    protected static TaskManager taskManager;

    public static void setup(Ui ui, Storage storage, TaskManager taskManager) {
        Command.ui = ui;
        Command.storage = storage;
        Command.taskManager = taskManager;
    }

    public abstract void execute() throws DukeCommandException;
}

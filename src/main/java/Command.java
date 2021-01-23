abstract class Command {
    protected static Ui ui;
    protected static TaskManager taskManager;

    public static void setup(Ui ui, TaskManager taskManager) {
        Command.ui = ui;
        Command.taskManager = taskManager;
    }

    public abstract void execute() throws DukeCommandException;

    public boolean isToExit() {
        return false;
    }
}

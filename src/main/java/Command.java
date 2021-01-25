public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    public Command(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public abstract void execute();

    public boolean isExit() {
        return false;
    }
}

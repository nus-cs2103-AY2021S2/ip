public abstract class Command {

    protected TaskList taskList;

    public Command() {
    }

    public void addTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean shouldExit() {
        return false;
    }

    public abstract boolean shouldSave();

    public abstract String execute() throws DukeException;
}

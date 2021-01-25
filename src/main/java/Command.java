public abstract class Command {
    protected final String taskDescription;

    public Command (String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public abstract void execute(TaskList taskList, Storage storage);

    public abstract boolean isExit();
}

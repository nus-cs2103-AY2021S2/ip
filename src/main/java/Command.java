abstract class Command {
    protected Tasks tasks;
    protected int targetIndex = -1;

    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Command() {
    }

    public void setTaskList(Tasks tasks) {
        this.tasks = tasks;
    }

    abstract CommandResult execute();
}
abstract class Command {
    protected Tasks tasks;
    protected int targetIndex = -1;

    public Command(Tasks tasks, int targetIndex) {
        this.tasks = tasks;
        this.targetIndex = targetIndex;
    }

    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Command(Tasks tasks) {
        this.tasks = tasks;
    }

    public Command() {
    }

    public void setTaskList(Tasks tasks) {
        this.tasks = tasks;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    abstract CommandResult execute();
}
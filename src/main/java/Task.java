public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public abstract Task finishTask();

    public abstract String saveTask();

    @Override
    public String toString() {
        return String.format("[%s] ", this.getStatusIcon()) + this.description ;
    }
}
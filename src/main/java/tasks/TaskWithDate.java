package tasks;

public abstract class TaskWithDate extends Task {

    public TaskWithDate(String description) {
        super(description);
    }

    protected TaskWithDate(String desc, Boolean isDone) {
        super(desc, isDone);
    }
}

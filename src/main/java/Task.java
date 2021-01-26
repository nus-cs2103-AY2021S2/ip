public class Task {

    protected String description;

    protected boolean isDone;

    protected int taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    public String markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        return toString();
    }

    @Override
    public String toString() {
        String indicator = getStatusIcon();
        return "[" + indicator + "]  " + description;
    }

    public int getTaskType() {
        return this.taskType;
    }
}

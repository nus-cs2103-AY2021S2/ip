public class Task {
//    public boolean isDeadline;
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskDate() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

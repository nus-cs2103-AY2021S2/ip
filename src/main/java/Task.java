public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : " "); //return tick if done
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }
}

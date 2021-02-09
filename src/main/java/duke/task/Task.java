package duke.task;

public class Task {
    private static final String TASK_TYPE = "TASK";
    private final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}

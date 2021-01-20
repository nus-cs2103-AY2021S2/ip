package duke;

public class Task {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                '}';
    }
}

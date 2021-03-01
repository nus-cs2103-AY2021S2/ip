package duke;

public class Pair {
    private final TaskList taskList;
    private final String message;

    public Pair(TaskList taskList, String message) {
        this.taskList = taskList;
        this.message = message;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getMessage() {
        return this.message;
    }
}

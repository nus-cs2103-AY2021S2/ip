package duke.task;



public class ToDo extends Task {
    private static final String TASK_TYPE = "TODO";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public  String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", TASK_TYPE, getStatusIcon(), getTaskDescription());
    }
}

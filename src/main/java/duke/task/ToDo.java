package duke.task;



public class ToDo extends Task {
    private static String taskType = "TODO";
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public  String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", taskType, getStatusIcon(), getTaskDescription());
    }
}

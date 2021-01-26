package duke;


public class ToDo extends Task{
    private static String taskType = "ToDo";
    public ToDo(String description) {
        super(description);
    }

    public  String getTaskType() {
        return taskType;
    }
}

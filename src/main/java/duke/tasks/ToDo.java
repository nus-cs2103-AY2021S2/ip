package duke.tasks;

public class ToDo extends Task {
    protected String taskName;

    public ToDo(String taskName) {
        super(taskName);
    }

    public String getTaskType() {
        return "ToDo";
    }

    @Override
    public String toSave() {
        return "T / " + super.isDoneString + super.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

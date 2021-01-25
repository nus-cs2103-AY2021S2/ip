package tasks;

import tasks.Task;

public class ToDo extends Task {
    protected String taskName;

    public ToDo(String taskName) {
        super(taskName);
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

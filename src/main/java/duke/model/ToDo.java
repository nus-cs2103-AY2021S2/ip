package duke.model;

public class ToDo extends Task {
    public ToDo(Boolean isCompleted, String taskName) {
        super('T', isCompleted, taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

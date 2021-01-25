package duke.model;

public class ToDo extends Task {
    public ToDo(Boolean markAsDone, String taskName) {
        super('T', markAsDone, taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(int done, String description) {
        super(done, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toStorageString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}

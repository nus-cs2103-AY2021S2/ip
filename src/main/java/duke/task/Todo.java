package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("T|%s|%s\n", status, super.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}

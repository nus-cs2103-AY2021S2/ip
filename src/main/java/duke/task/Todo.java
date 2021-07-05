package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of Todo
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of Todo to be saved in data file
     *
     * @return String
     */
    public String toSavedString() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }

    public Task clone() {
        return new Todo(description, isDone);
    }
}

package main.java;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markAsDone() {
        return new Todo(description, true);
    }

    public String fileFormat() {
        return "T | " + (super.isDone ? "1 | " : "0 | ") + this.description;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}

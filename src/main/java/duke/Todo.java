package duke;

/**
 * A type of Task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, "", "", "[T]", false);
    }
}
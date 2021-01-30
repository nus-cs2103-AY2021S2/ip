package duke.task;

/**
 * Task type todo.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveTime() {
        return "";
    }

    @Override
    public String getSaveType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}

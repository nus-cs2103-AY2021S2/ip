/**
 * Implementation for tasks with no schedules assigned.
 */

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

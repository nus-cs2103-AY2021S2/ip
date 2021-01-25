package duke.task;

public class Todo extends Task {
    public Todo(int isDone, String description) {
        super('T', isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

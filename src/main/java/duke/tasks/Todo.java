package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String data() {
        return String.format("T | %s", super.data());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

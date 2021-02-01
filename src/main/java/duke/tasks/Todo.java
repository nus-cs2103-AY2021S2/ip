package duke.tasks;

public class Todo extends Task{
    private static final String TYPE = "TODO";
    public Todo(String description) {
        super(description, TYPE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

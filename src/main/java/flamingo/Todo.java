package flamingo;

/**
 * Creates a Todo Task with a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveTask() {
        return "T" + super.saveTask() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

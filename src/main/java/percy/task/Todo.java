package percy.task;

public class Todo extends Task {
    public static final String PREFIX = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString();
    }

    @Override
    public String formatToFile() {
        return PREFIX + " | " + super.formatToFile();
    }
}

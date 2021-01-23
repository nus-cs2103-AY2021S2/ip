public class ToDoTask extends Task {
    public static final String IDENTIFIER = "T";

    public ToDoTask(String name) {
        super(IDENTIFIER, name);
    }

    public ToDoTask(String name, boolean isCompleted) {
        super(IDENTIFIER, name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

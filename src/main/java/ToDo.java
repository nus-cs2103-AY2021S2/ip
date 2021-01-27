public class ToDo extends Task {
    public final String SHORT_HAND = "T";

    public ToDo (String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[" + SHORT_HAND +  "]" + super.toString();
    }
}

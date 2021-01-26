package duke;

public class ToDoTask extends Task {
    public ToDoTask(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "T" +  divider
                + (isDone ? "1" : "0") + divider
                + description;
    }
}

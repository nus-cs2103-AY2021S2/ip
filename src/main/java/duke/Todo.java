package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getFormattedString() {
        return "TODO::" + (isDone? "1::" : "0::") + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + getDate() + "\n";
    }
}

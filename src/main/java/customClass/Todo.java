package customClass;

import customClass.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String saveString() {
        return isDone ? "T --- 1 --- " + description : "T --- 0 --- " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Todo extends Task {
    public Todo(String toDoDescription) {
        super(toDoDescription);
    }

    @Override
    public String toString() {
        return "[T][" + (done ? "X" : " ") + "] " + taskDescription;
    }
}
